package dto.response;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class ResultResponse {
    private UUID electionId;
    private Map<UUID, Long> votesByCandidate = new LinkedHashMap<>();

    public ResultResponse(UUID electionId, Map<UUID, Long> votesByCandidate) {
        this.electionId = electionId;
        this.votesByCandidate = votesByCandidate;
    }

    public UUID getElectionId() { return electionId; }
    public Map<UUID, Long> getVotesByCandidate() { return votesByCandidate; }
}