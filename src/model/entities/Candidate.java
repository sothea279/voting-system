package model.entities;

import java.util.UUID;

public class Candidate {
    private UUID id;
    private UUID electionId;
    private String name;
    private String party;
    private String description;

    public Candidate(UUID id, UUID electionId, String name, String party, String description) {
        this.id = id;
        this.electionId = electionId;
        this.name = name;
        this.party = party;
        this.description = description;
    }

    public UUID getId() { return id; }
    public UUID getElectionId() { return electionId; }
    public String getName() { return name; }
    public String getParty() { return party; }
    public String getDescription() { return description; }
}