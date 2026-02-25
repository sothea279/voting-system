package model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Election {
    private UUID id;
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String status;

    public Election(UUID id, String title, LocalDateTime startAt, LocalDateTime endAt, String status) {
        this.id = id;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.status = status;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public LocalDateTime getStartAt() { return startAt; }
    public LocalDateTime getEndAt() { return endAt; }
    public String getStatus() { return status; }
}