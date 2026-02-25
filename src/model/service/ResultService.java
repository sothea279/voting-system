package model.service;

import dto.response.ResultResponse;
import model.dao.VoteDao;

import java.util.Map;
import java.util.UUID;

public class ResultService {
    private final TransactionManager tx = new TransactionManager();
    private final VoteDao voteDao = new VoteDao();

    public ResultResponse getResults(UUID electionId) {
        return tx.inTransaction(conn -> {
            Map<UUID, Long> map = voteDao.countByCandidate(conn, electionId);
            return new ResultResponse(electionId, map);
        });
    }
}