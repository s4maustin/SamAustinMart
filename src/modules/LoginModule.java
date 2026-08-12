package modules;

import models.User;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginModule {
    public static User login(ArrayList<User> users, Scanner scanner) {
        System.out.println("\n========== LOGIN ==========");

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password)) {
                System.out.println("\nLogin successful!");
                System.out.println("Welcome " + user.getName() + "!");
                return user;
            }
        }

        System.out.println("Invalid email or password.");
        return null;
    }
}
