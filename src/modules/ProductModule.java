package modules;

import models.Product;
import models.User;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductModule {
    private static int nextProductId = 4;

    public static void addProduct(ArrayList<Product> products, User seller, Scanner scanner) {
        System.out.println("\n========== ADD PRODUCT ==========");

        System.out.print("Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.print("Category: ");
        String category = scanner.nextLine();

        Product product = new Product(nextProductId++, name, description,
                price, stock, category, seller.getUserId());

        products.add(product);

        System.out.println("\nProduct added successfully!");
        product.displayProduct();
    }

    public static void viewAllProducts(ArrayList<Product> products) {
        System.out.println("\n========== ALL PRODUCTS ==========");

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product product : products) {
            product.displayProduct();
        }
    }

    public static void viewSellerProducts(ArrayList<Product> products, User seller) {
        System.out.println("\n========== MY PRODUCTS ==========");
        boolean found = false;

        for (Product product : products) {
            if (product.getSellerId() == seller.getUserId()) {
                product.displayProduct();
                found = true;
            }
        }

        if (!found) {
            System.out.println("You have not added any products.");
        }
    }

    public static void updateProduct(ArrayList<Product> products, User seller, Scanner scanner) {
        viewSellerProducts(products, seller);

        System.out.print("\nEnter Product ID to update: ");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = findSellerProduct(products, productId, seller);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("New Product Name: ");
        product.setName(scanner.nextLine());

        System.out.print("New Description: ");
        product.setDescription(scanner.nextLine());

        System.out.print("New Price: ");
        product.setPrice(Double.parseDouble(scanner.nextLine()));

        System.out.print("New Stock: ");
        product.setStock(Integer.parseInt(scanner.nextLine()));

        System.out.print("New Category: ");
        product.setCategory(scanner.nextLine());

        System.out.println("Product updated successfully!");
    }

    public static void deleteProduct(ArrayList<Product> products, User seller, Scanner scanner) {
        viewSellerProducts(products, seller);

        System.out.print("\nEnter Product ID to delete: ");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = findSellerProduct(products, productId, seller);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        products.remove(product);
        System.out.println("Product deleted successfully!");
    }

    private static Product findSellerProduct(ArrayList<Product> products, int productId, User seller) {
        for (Product product : products) {
            if (product.getProductId() == productId
                    && product.getSellerId() == seller.getUserId()) {
                return product;
            }
        }
        return null;
    }
}
