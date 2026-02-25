package model.service;

import database.DbConnection;

import java.sql.Connection;

public class TransactionManager {

    public interface TxWork<T> {
        T execute(Connection conn) throws Exception;
    }

    public <T> T inTransaction(TxWork<T> work) {
        try (Connection conn = DbConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                T result = work.execute(conn);
                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                throw new RuntimeException("Transaction failed: " + e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new RuntimeException("DB error: " + e.getMessage(), e);
        }
    }
}