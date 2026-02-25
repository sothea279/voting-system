package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

public class AuditDao {

    public Optional<String> getLastHash(Connection conn) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT curr_hash FROM audit_logs ORDER BY created_at DESC LIMIT 1
        """)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.ofNullable(rs.getString("curr_hash"));
            }
        }
    }

    public void insert(Connection conn, UUID id, String action, UUID actorId, String metadata, String prevHash, String currHash) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            INSERT INTO audit_logs (id, action, actor_id, metadata, prev_hash, curr_hash)
            VALUES (?, ?, ?, ?, ?, ?)
        """)) {
            ps.setObject(1, id);
            ps.setString(2, action);
            ps.setObject(3, actorId);
            ps.setString(4, metadata);
            ps.setString(5, prevHash);
            ps.setString(6, currHash);
            ps.executeUpdate();
        }
    }
}