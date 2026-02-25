package view;

import controller.ResultController;
import controller.VoteController;
import dto.request.VoteRequest;
import model.entities.User;

import java.util.Scanner;
import java.util.UUID;

public class VoterView {
    private final Scanner sc = new Scanner(System.in);

    private final VoteController voteController = new VoteController();
    private final ResultController resultController = new ResultController();

    public void show(User voter) {
        while (true) {
            System.out.println("\n=== Voter Menu ===");
            System.out.println("1) Vote");
            System.out.println("2) View Results");
            System.out.println("3) Back");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            if ("1".equals(choice)) {
                System.out.print("Election ID: ");
                UUID electionId = UUID.fromString(sc.nextLine());

                System.out.print("Candidate ID: ");
                UUID candidateId = UUID.fromString(sc.nextLine());

                var res = voteController.vote(voter, new VoteRequest(electionId, candidateId));
                System.out.println(res.getMessage());

            } else if ("2".equals(choice)) {
                System.out.print("Election ID: ");
                UUID electionId = UUID.fromString(sc.nextLine());

                var res = resultController.getElectionResults(electionId);
                System.out.println("Results for election: " + res.getElectionId());
                res.getVotesByCandidate().forEach((cid, total) ->
                        System.out.println("Candidate " + cid + " => " + total + " votes")
                );

            } else if ("3".equals(choice)) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}