package model.entities;

import java.util.UUID;

public class User {
    private UUID id;
    private String fullName;
    private String email;
    private String passwordHash;
    private String role;

    public User(UUID id, String fullName, String email, String passwordHash, String role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
}