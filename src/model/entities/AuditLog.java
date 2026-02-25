package model.entities;

import java.util.UUID;

public class AuditLog {
    private UUID id;
    private String action;
    private UUID actorId;
    private String metadata;
    private String prevHash;
    private String currHash;

    public AuditLog(UUID id, String action, UUID actorId, String metadata, String prevHash, String currHash) {
        this.id = id;
        this.action = action;
        this.actorId = actorId;
        this.metadata = metadata;
        this.prevHash = prevHash;
        this.currHash = currHash;
    }

    public UUID getId() { return id; }
    public String getAction() { return action; }
    public UUID getActorId() { return actorId; }
    public String getMetadata() { return metadata; }
    public String getPrevHash() { return prevHash; }
    public String getCurrHash() { return currHash; }
}