import java.util.Scanner;

public class SamAustinMart {

    static Scanner sc = new Scanner(System.in);

    // Account details
    static String registeredEmail = "";
    static String registeredPassword = "";
    static String registeredRole = "";

    // ================= CREATE ACCOUNT MODULE =================
    public static void createAccount() {

        System.out.println("\n================================");
        System.out.println("        CREATE ACCOUNT");
        System.out.println("================================");

        System.out.print("Enter your Email: ");
        registeredEmail = sc.nextLine();

        System.out.print("Create your Password: ");
        registeredPassword = sc.nextLine();

        System.out.print("Enter your Role (User/Seller): ");
        registeredRole = sc.nextLine();

        // Role validation
        if (!registeredRole.equalsIgnoreCase("User") &&
            !registeredRole.equalsIgnoreCase("Seller")) {

            System.out.println("\nInvalid role!");
            System.out.println("Please enter User or Seller.");
            return;
        }

        System.out.println("\nAccount created successfully!");
        System.out.println("Email: " + registeredEmail);
        System.out.println("Role: " + registeredRole);
    }


    // ================= LOGIN MODULE =================
    public static void login() {

        System.out.println("\n================================");
        System.out.println("             LOGIN");
        System.out.println("================================");

        if (registeredEmail.isEmpty()) {
            System.out.println("No account found!");
            System.out.println("Please create an account first.");
            return;
        }

        System.out.print("Enter your Email: ");
        String email = sc.nextLine();

        System.out.print("Enter your Password: ");
        String password = sc.nextLine();

        System.out.print("Enter your Role (User/Seller): ");
        String role = sc.nextLine();

        // Login validation
        if (email.equals(registeredEmail) &&
            password.equals(registeredPassword) &&
            role.equalsIgnoreCase(registeredRole)) {

            System.out.println("\nLogin Successful!");

            if (role.equalsIgnoreCase("User")) {
                System.out.println("Welcome User!");
                System.out.println("You can browse and purchase products.");

            } else if (role.equalsIgnoreCase("Seller")) {
                System.out.println("Welcome Seller!");
                System.out.println("You can add and manage products.");
            }

        } else {
            System.out.println("\nInvalid email, password, or role!");
            System.out.println("Login failed.");
        }
    }


    // ================= MAIN MODULE =================
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("       Welcome to SamAustinMart");
            System.out.println("================================");

            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("\nThank you for using SamAustinMart!");
                    sc.close();
                    return;

                default:
                    System.out.println("\nInvalid choice!");
                    System.out.println("Please select 1, 2, or 3.");
            }
        }
    }
}