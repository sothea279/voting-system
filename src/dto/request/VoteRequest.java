package dto.request;

import java.util.UUID;

public class VoteRequest {
    private UUID electionId;
    private UUID candidateId;

    public VoteRequest() {}

    public VoteRequest(UUID electionId, UUID candidateId) {
        this.electionId = electionId;
        this.candidateId = candidateId;
    }

    public UUID getElectionId() { return electionId; }
    public UUID getCandidateId() { return candidateId; }
}