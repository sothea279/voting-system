package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class VoteDao {

    public void insert(Connection conn, UUID id, UUID electionId, UUID candidateId, String tokenHash, String voteHash) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            INSERT INTO votes (id, election_id, candidate_id, token_hash, vote_hash)
            VALUES (?, ?, ?, ?, ?)
        """)) {
            ps.setObject(1, id);
            ps.setObject(2, electionId);
            ps.setObject(3, candidateId);
            ps.setString(4, tokenHash);
            ps.setString(5, voteHash);
            ps.executeUpdate();
        }
    }

    public Map<UUID, Long> countByCandidate(Connection conn, UUID electionId) throws Exception {
        Map<UUID, Long> map = new LinkedHashMap<>();
        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT candidate_id, COUNT(*) AS total
            FROM votes
            WHERE election_id = ?
            GROUP BY candidate_id
        """)) {
            ps.setObject(1, electionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UUID cid = UUID.fromString(rs.getString("candidate_id"));
                    long total = rs.getLong("total");
                    map.put(cid, total);
                }
            }
        }
        return map;
    }
}