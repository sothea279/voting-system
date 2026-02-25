package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

public class TokenDao {

    public Optional<String> getUnusedTokenHash(Connection conn, UUID userId, UUID electionId) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT token_hash FROM voter_tokens
            WHERE user_id = ? AND election_id = ? AND used_at IS NULL
        """)) {
            ps.setObject(1, userId);
            ps.setObject(2, electionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(rs.getString("token_hash"));
            }
        }
    }

    public void issueIfNotExists(Connection conn, UUID userId, UUID electionId, String tokenHash) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            INSERT INTO voter_tokens (user_id, election_id, token_hash)
            VALUES (?, ?, ?)
            ON CONFLICT (user_id, election_id) DO NOTHING
        """)) {
            ps.setObject(1, userId);
            ps.setObject(2, electionId);
            ps.setString(3, tokenHash);
            ps.executeUpdate();
        }
    }

    public void markUsed(Connection conn, UUID userId, UUID electionId) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            UPDATE voter_tokens SET used_at = NOW()
            WHERE user_id = ? AND election_id = ? AND used_at IS NULL
        """)) {
            ps.setObject(1, userId);
            ps.setObject(2, electionId);
            ps.executeUpdate();
        }
    }
}