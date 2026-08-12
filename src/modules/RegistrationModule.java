package modules;

import models.User;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationModule {
    private static int nextUserId = 3;

    public static User registerUser(ArrayList<User> users, Scanner scanner) {
        System.out.println("\n========== REGISTRATION ==========");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        if (emailExists(users, email)) {
            System.out.println("Email already registered!");
            return null;
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.println("\nSelect Role:");
        System.out.println("1. Buyer");
        System.out.println("2. Seller");
        System.out.print("Enter choice: ");

        int choice = Integer.parseInt(scanner.nextLine());
        String role;

        if (choice == 1) {
            role = "BUYER";
        } else if (choice == 2) {
            role = "SELLER";
        } else {
            System.out.println("Invalid role!");
            return null;
        }

        User user = new User(nextUserId++, name, email, password, role);
        users.add(user);

        System.out.println("\nRegistration successful!");
        System.out.println("Your User ID: " + user.getUserId());
        return user;
    }

    private static boolean emailExists(ArrayList<User> users, String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}
