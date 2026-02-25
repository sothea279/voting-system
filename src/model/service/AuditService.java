package model.service;

import model.dao.AuditDao;
import utils.HashUtil;

import java.sql.Connection;
import java.time.Instant;
import java.util.UUID;

public class AuditService {
    private final AuditDao auditDao = new AuditDao();

    public void log(Connection conn, String action, UUID actorId, String metadata) throws Exception {
        String prev = auditDao.getLastHash(conn).orElse(null);

        // Hash chain payload
        String payload = action + "|" + actorId + "|" + metadata + "|" + Instant.now() + "|" + prev;
        String curr = HashUtil.sha256(payload);

        auditDao.insert(conn, UUID.randomUUID(), action, actorId, metadata, prev, curr);
    }
}