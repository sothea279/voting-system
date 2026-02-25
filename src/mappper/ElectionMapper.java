package mappper;

import model.entities.Election;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ElectionMapper {
    public static Election map(ResultSet rs) throws SQLException {
        return new Election(
                UUID.fromString(rs.getString("id")),
                rs.getString("title"),
                rs.getTimestamp("start_at").toLocalDateTime(),
                rs.getTimestamp("end_at").toLocalDateTime(),
                rs.getString("status")
        );
    }
}