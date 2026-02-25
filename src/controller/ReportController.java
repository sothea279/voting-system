package controller;

import dto.response.ApiResponse;
import model.service.ReportService;

import java.util.UUID;

public class ReportController {
    private final ReportService reportService = new ReportService();

    public ApiResponse exportElectionReport(UUID electionId) {
        reportService.exportElectionReport(electionId); // stub
        return ApiResponse.ok("Report generated (stub)");
    }
}