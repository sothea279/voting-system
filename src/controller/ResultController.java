package controller;

import dto.response.ResultResponse;
import model.service.ResultService;

import java.util.UUID;

public class ResultController {
    private final ResultService resultService = new ResultService();

    public ResultResponse getElectionResults(UUID electionId) {
        return resultService.getResults(electionId);
    }
}