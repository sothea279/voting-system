package model.dao;

import mapper.UserMapper;
import model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

public class UserDao {

    public void insert(Connection conn, User user) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            INSERT INTO users (id, full_name, email, password_hash, role)
            VALUES (?, ?, ?, ?, ?)
        """)) {
            ps.setObject(1, user.getId());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPasswordHash());
            ps.setString(5, user.getRole());
            ps.executeUpdate();
        }
    }

    public Optional<User> findByEmail(Connection conn, String email) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT * FROM users WHERE email = ?
        """)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(UserMapper.map(rs));
            }
        }
    }
}