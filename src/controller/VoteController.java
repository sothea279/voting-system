package controller;

import dto.request.VoteRequest;
import dto.response.ApiResponse;
import model.entities.User;
import model.service.VotingService;

public class VoteController {
    private final VotingService votingService = new VotingService();

    public ApiResponse vote(User voter, VoteRequest req) {
        votingService.castVote(voter.getId(), req.getElectionId(), req.getCandidateId());
        return ApiResponse.ok("Vote success");
    }
}