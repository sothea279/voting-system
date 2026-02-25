package model.service;

import dto.request.CandidateCreateRequest;
import model.dao.CandidateDao;
import model.entities.Candidate;
import utils.ValidationUtil;

import java.util.UUID;

public class CandidateService {
    private final TransactionManager tx = new TransactionManager();
    private final CandidateDao candidateDao = new CandidateDao();

    public void createCandidate(CandidateCreateRequest req) {
        ValidationUtil.requireNotNull(req.getElectionId(), "Election ID");
        ValidationUtil.requireNotBlank(req.getName(), "Candidate name");

        tx.inTransaction(conn -> {
            Candidate c = new Candidate(
                    UUID.randomUUID(),
                    req.getElectionId(),
                    req.getName(),
                    req.getParty(),
                    req.getDescription()
            );
            candidateDao.insert(conn, c);
            return null;
        });
    }
}