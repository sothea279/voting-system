package mappper;

import model.entities.AuditLog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AuditMapper {
    public static AuditLog map(ResultSet rs) throws SQLException {
        String actor = rs.getString("actor_id");
        return new AuditLog(
                UUID.fromString(rs.getString("id")),
                rs.getString("action"),
                actor == null ? null : UUID.fromString(actor),
                rs.getString("metadata"),
                rs.getString("prev_hash"),
                rs.getString("curr_hash")
        );
    }
}