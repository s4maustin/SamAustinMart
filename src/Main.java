import models.CartItem;
import models.Product;
import models.User;

import modules.CartModule;
import modules.CheckoutModule;
import modules.LoginModule;
import modules.ProductModule;
import modules.RegistrationModule;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<CartItem> cart = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        addSampleUsers();
        addSampleProducts();

        while (true) {
            displayMainMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    RegistrationModule.registerUser(users, scanner);
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    ProductModule.viewAllProducts(products);
                    break;
                case 4:
                    exitApplication();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ================================
    // MAIN MENU
    // ================================

    private static void displayMainMenu() {
        System.out.println("\n====================================");
        System.out.println("          SAMAUSTINMART");
        System.out.println("====================================");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. View Products");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    // ================================
    // LOGIN
    // ================================

    private static void loginUser() {
        User user = LoginModule.login(users, scanner);

        if (user == null) {
            return;
        }

        if (user.getRole().equals("SELLER")) {
            sellerMenu(user);
        } else {
            buyerMenu(user);
        }
    }

    // ================================
    // SELLER MENU
    // ================================

    private static void sellerMenu(User seller) {
        while (true) {
            System.out.println("\n========== SAMAUSTINMART SELLER ==========");
            System.out.println("1. Add Product");
            System.out.println("2. View My Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. View All Products");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    ProductModule.addProduct(products, seller, scanner);
                    break;
                case 2:
                    ProductModule.viewSellerProducts(products, seller);
                    break;
                case 3:
                    ProductModule.updateProduct(products, seller, scanner);
                    break;
                case 4:
                    ProductModule.deleteProduct(products, seller, scanner);
                    break;
                case 5:
                    ProductModule.viewAllProducts(products);
                    break;
                case 6:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ================================
    // BUYER MENU
    // ================================

    private static void buyerMenu(User buyer) {
        while (true) {
            System.out.println("\n========== SAMAUSTINMART BUYER ==========");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Cart Quantity");
            System.out.println("5. Remove from Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Logout");
            System.out.print("Enter choice: ");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    ProductModule.viewAllProducts(products);
                    break;
                case 2:
                    CartModule.addToCart(products, cart, scanner);
                    break;
                case 3:
                    CartModule.viewCart(cart);
                    break;
                case 4:
                    CartModule.updateQuantity(cart, scanner);
                    break;
                case 5:
                    CartModule.removeFromCart(cart, scanner);
                    break;
                case 6:
                    CheckoutModule.checkout(buyer.getUserId(), cart, scanner);
                    break;
                case 7:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ================================
    // INPUT
    // ================================

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number.");
            return -1;
        }
    }

    // ================================
    // SAMPLE DATA
    // ================================

    private static void addSampleUsers() {
        users.add(new User(
                1,
                "Demo Seller",
                "seller@gmail.com",
                "1234",
                "SELLER"
        ));

        users.add(new User(
                2,
                "Demo Buyer",
                "buyer@gmail.com",
                "1234",
                "BUYER"
        ));
    }

    private static void addSampleProducts() {
        products.add(new Product(
                1,
                "Gaming Mouse",
                "RGB Gaming Mouse",
                1200,
                10,
                "Electronics",
                1
        ));

        products.add(new Product(
                2,
                "Mechanical Keyboard",
                "RGB Mechanical Keyboard",
                3500,
                5,
                "Electronics",
                1
        ));

        products.add(new Product(
                3,
                "Laptop Stand",
                "Adjustable Laptop Stand",
                999,
                8,
                "Accessories",
                1
        ));
    }

    // ================================
    // EXIT
    // ================================

    private static void exitApplication() {
        System.out.println(
                "\nThank you for using SamAustinMart!"
        );
        scanner.close();
    }
}
