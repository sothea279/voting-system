package mapper;

import model.entities.Candidate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CandidateMapper {
    public static Candidate map(ResultSet rs) throws SQLException {
        return new Candidate(
                UUID.fromString(rs.getString("id")),
                UUID.fromString(rs.getString("election_id")),
                rs.getString("name"),
                rs.getString("party"),
                rs.getString("description")
        );
    }
}