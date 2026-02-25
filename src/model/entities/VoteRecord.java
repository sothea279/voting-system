package model.entities;

import java.util.UUID;

public class VoteRecord {
    private UUID id;
    private UUID electionId;
    private UUID candidateId;
    private String tokenHash;
    private String voteHash;

    public VoteRecord(UUID id, UUID electionId, UUID candidateId, String tokenHash, String voteHash) {
        this.id = id;
        this.electionId = electionId;
        this.candidateId = candidateId;
        this.tokenHash = tokenHash;
        this.voteHash = voteHash;
    }

    public UUID getId() { return id; }
    public UUID getElectionId() { return electionId; }
    public UUID getCandidateId() { return candidateId; }
    public String getTokenHash() { return tokenHash; }
    public String getVoteHash() { return voteHash; }
}