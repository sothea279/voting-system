package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

public class ElectionDao {

    public boolean isOpen(Connection conn, UUID electionId) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT status FROM elections WHERE id = ?
        """)) {
            ps.setObject(1, electionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return false;
                return "OPEN".equalsIgnoreCase(rs.getString("status"));
            }
        }
    }

    public Optional<UUID> getFirstElectionId(Connection conn) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT id FROM elections ORDER BY start_at ASC LIMIT 1
        """)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(UUID.fromString(rs.getString("id")));
            }
        }
    }
}