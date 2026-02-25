package database;

import java.sql.Connection;
import java.sql.Statement;

public class DbMigrate {

    public static void migrate() {
        // DB PROCESS: schema creation (friend can edit here)
        try (Connection conn = DbConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute(""" 
                CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id UUID PRIMARY KEY,
                    full_name VARCHAR(120) NOT NULL,
                    email VARCHAR(120) UNIQUE NOT NULL,
                    password_hash TEXT NOT NULL,
                    role VARCHAR(20) NOT NULL,
                    created_at TIMESTAMP NOT NULL DEFAULT NOW()
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS elections (
                    id UUID PRIMARY KEY,
                    title VARCHAR(150) NOT NULL,
                    start_at TIMESTAMP NOT NULL,
                    end_at TIMESTAMP NOT NULL,
                    status VARCHAR(20) NOT NULL
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS candidates (
                    id UUID PRIMARY KEY,
                    election_id UUID NOT NULL REFERENCES elections(id),
                    name VARCHAR(120) NOT NULL,
                    party VARCHAR(120),
                    description TEXT,
                    created_at TIMESTAMP NOT NULL DEFAULT NOW()
                );
            """);

            // Token table: prevents duplicate vote but keeps vote anonymous
            st.execute("""
                CREATE TABLE IF NOT EXISTS voter_tokens (
                    user_id UUID NOT NULL REFERENCES users(id),
                    election_id UUID NOT NULL REFERENCES elections(id),
                    token_hash TEXT NOT NULL UNIQUE,
                    issued_at TIMESTAMP NOT NULL DEFAULT NOW(),
                    used_at TIMESTAMP NULL,
                    PRIMARY KEY (user_id, election_id)
                );
            """);

            // Votes table: NO user_id (anonymous)
            st.execute("""
                CREATE TABLE IF NOT EXISTS votes (
                    id UUID PRIMARY KEY,
                    election_id UUID NOT NULL REFERENCES elections(id),
                    candidate_id UUID NOT NULL REFERENCES candidates(id),
                    token_hash TEXT NOT NULL UNIQUE,
                    vote_hash TEXT NOT NULL,
                    created_at TIMESTAMP NOT NULL DEFAULT NOW()
                );
            """);

            // Audit chain table
            st.execute("""
                CREATE TABLE IF NOT EXISTS audit_logs (
                    id UUID PRIMARY KEY,
                    action VARCHAR(80) NOT NULL,
                    actor_id UUID NULL,
                    metadata TEXT NULL,
                    prev_hash TEXT NULL,
                    curr_hash TEXT NOT NULL,
                    created_at TIMESTAMP NOT NULL DEFAULT NOW()
                );
            """);

            // Seed demo election if none exists (easy testing)
            st.execute("""
                INSERT INTO elections (id, title, start_at, end_at, status)
                SELECT uuid_generate_v4(), 'Demo Election', NOW() - INTERVAL '1 day', NOW() + INTERVAL '7 day', 'OPEN'
                WHERE NOT EXISTS (SELECT 1 FROM elections);
            """);

        } catch (Exception e) {
            throw new RuntimeException("Migration failed: " + e.getMessage(), e);
        }
    }
}