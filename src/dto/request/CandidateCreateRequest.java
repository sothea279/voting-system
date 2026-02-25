package dto.request;

import java.util.UUID;

public class CandidateCreateRequest {
    private UUID electionId;
    private String name;
    private String party;
    private String description;

    public CandidateCreateRequest() {}

    public CandidateCreateRequest(UUID electionId, String name, String party, String description) {
        this.electionId = electionId;
        this.name = name;
        this.party = party;
        this.description = description;
    }

    public UUID getElectionId() { return electionId; }
    public String getName() { return name; }
    public String getParty() { return party; }
    public String getDescription() { return description; }
}