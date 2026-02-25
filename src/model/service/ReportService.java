package model.service;

import java.util.UUID;

public class ReportService {
    public void exportElectionReport(UUID electionId) {
        // JasperReports integration point (friend implements)
        // DB PROCESS: query results + compile jrxml + export pdf
    }
}