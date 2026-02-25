package model.service;

import model.dao.*;
import utils.HashUtil;

import java.time.Instant;
import java.util.UUID;

public class VotingService {
    private final TransactionManager tx = new TransactionManager();

    private final ElectionDao electionDao = new ElectionDao();
    private final CandidateDao candidateDao = new CandidateDao();
    private final VoteDao voteDao = new VoteDao();
    private final TokenDao tokenDao = new TokenDao();
    private final AuditService auditService = new AuditService();

    public void castVote(UUID userId, UUID electionId, UUID candidateId) {
        tx.inTransaction(conn -> {
            if (!electionDao.isOpen(conn, electionId)) {
                throw new IllegalStateException("Election is not open");
            }

            // Get or create a token for this user+election (anonymous)
            String tokenHash = tokenDao.getUnusedTokenHash(conn, userId, electionId).orElse(null);
            if (tokenHash == null) {
                String raw = UUID.randomUUID() + "|" + userId + "|" + electionId;
                tokenHash = HashUtil.sha256(raw);
                tokenDao.issueIfNotExists(conn, userId, electionId, tokenHash);

                // re-check unused token (in case already existed)
                tokenHash = tokenDao.getUnusedTokenHash(conn, userId, electionId).orElse(null);
                if (tokenHash == null) {
                    throw new IllegalStateException("You already voted in this election");
                }
            }

            if (!candidateDao.belongsToElection(conn, candidateId, electionId)) {
                throw new IllegalArgumentException("Candidate not in this election");
            }

            UUID voteId = UUID.randomUUID();
            String voteContent = electionId + "|" + candidateId + "|" + tokenHash + "|" + Instant.now();
            String voteHash = HashUtil.sha256(voteContent);

            voteDao.insert(conn, voteId, electionId, candidateId, tokenHash, voteHash);
            tokenDao.markUsed(conn, userId, electionId);

            auditService.log(conn, "CAST_VOTE", userId,
                    "{voteId:" + voteId + ", electionId:" + electionId + ", candidateId:" + candidateId + "}"
            );

            return null;
        });
    }
}