package view;

import controller.AuthController;
import dto.request.LoginRequest;
import dto.request.RegisterRequest;
import dto.response.LoginResponse;
import model.entities.User;

import java.util.Scanner;

public class MenuView {
    private final Scanner sc = new Scanner(System.in);

    private final AuthController authController = new AuthController();
    private final AdminView adminView = new AdminView();
    private final VoterView voterView = new VoterView();

    public void show() {
        while (true) {
            System.out.println("\n=== Transparent Online Voting System ===");
            System.out.println("1) Register");
            System.out.println("2) Login");
            System.out.println("3) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            if ("1".equals(choice)) {
                System.out.print("Full name: ");
                String fullName = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                LoginResponse res = authController.register(new RegisterRequest(fullName, email, pass));
                System.out.println(res.getMessage());

            } else if ("2".equals(choice)) {
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                LoginResponse res = authController.login(new LoginRequest(email, pass));
                System.out.println(res.getMessage());

                if (res.isSuccess()) {
                    User currentUser = res.getUser();
                    if ("ADMIN".equalsIgnoreCase(currentUser.getRole())) {
                        adminView.show(currentUser);
                    } else {
                        voterView.show(currentUser);
                    }
                }

            } else if ("3".equals(choice)) {
                System.out.println("Bye.");
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}