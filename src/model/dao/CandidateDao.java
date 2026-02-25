package model.dao;

import mapper.CandidateMapper;
import model.entities.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CandidateDao {

    public void insert(Connection conn, Candidate c) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            INSERT INTO candidates (id, election_id, name, party, description)
            VALUES (?, ?, ?, ?, ?)
        """)) {
            ps.setObject(1, c.getId());
            ps.setObject(2, c.getElectionId());
            ps.setString(3, c.getName());
            ps.setString(4, c.getParty());
            ps.setString(5, c.getDescription());
            ps.executeUpdate();
        }
    }

    public boolean belongsToElection(Connection conn, UUID candidateId, UUID electionId) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT 1 FROM candidates WHERE id = ? AND election_id = ?
        """)) {
            ps.setObject(1, candidateId);
            ps.setObject(2, electionId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public List<Candidate> findByElection(Connection conn, UUID electionId) throws Exception {
        List<Candidate> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT * FROM candidates WHERE election_id = ? ORDER BY created_at ASC
        """)) {
            ps.setObject(1, electionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(CandidateMapper.map(rs));
            }
        }
        return list;
    }
}