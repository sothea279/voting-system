package mapper;

import model.entities.VoteRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class VoteMapper {
    public static VoteRecord map(ResultSet rs) throws SQLException {
        return new VoteRecord(
                UUID.fromString(rs.getString("id")),
                UUID.fromString(rs.getString("election_id")),
                UUID.fromString(rs.getString("candidate_id")),
                rs.getString("token_hash"),
                rs.getString("vote_hash")
        );
    }
}