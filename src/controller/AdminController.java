package controller;

import dto.request.CandidateCreateRequest;
import dto.response.ApiResponse;
import model.entities.User;
import model.service.CandidateService;
import utils.ValidationUtil;

public class AdminController {
    private final CandidateService candidateService = new CandidateService();

    public ApiResponse addCandidate(User currentUser, CandidateCreateRequest req) {
        ValidationUtil.requireAdmin(currentUser);
        candidateService.createCandidate(req);
        return ApiResponse.ok("Candidate created");
    }
}