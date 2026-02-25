package view;

import controller.AdminController;
import dto.request.CandidateCreateRequest;
import model.entities.User;

import java.util.Scanner;
import java.util.UUID;

public class AdminView {
    private final Scanner sc = new Scanner(System.in);
    private final AdminController adminController = new AdminController();

    public void show(User admin) {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1) Add Candidate");
            System.out.println("2) Back");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            if ("1".equals(choice)) {
                System.out.print("Election ID: ");
                UUID electionId = UUID.fromString(sc.nextLine());

                System.out.print("Candidate name: ");
                String name = sc.nextLine();

                System.out.print("Party: ");
                String party = sc.nextLine();

                System.out.print("Description: ");
                String desc = sc.nextLine();

                var res = adminController.addCandidate(admin, new CandidateCreateRequest(electionId, name, party, desc));
                System.out.println(res.getMessage());

            } else if ("2".equals(choice)) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}