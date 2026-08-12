package modules;

import models.CartItem;
import models.Product;
import java.util.ArrayList;
import java.util.Scanner;

public class CartModule {

    public static void addToCart(ArrayList<Product> products,
                                 ArrayList<CartItem> cart,
                                 Scanner scanner) {
        ProductModule.viewAllProducts(products);

        if (products.isEmpty()) return;

        System.out.print("\nEnter Product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = findProduct(products, productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if (product.getStock() <= 0) {
            System.out.println("Product is out of stock.");
            return;
        }

        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        if (quantity <= 0 || quantity > product.getStock()) {
            System.out.println("Invalid or unavailable quantity.");
            return;
        }

        CartItem existingItem = findCartItem(cart, productId);

        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + quantity;

            if (newQuantity > product.getStock()) {
                System.out.println("Cannot add more than available stock.");
                return;
            }

            existingItem.setQuantity(newQuantity);
        } else {
            cart.add(new CartItem(product, quantity));
        }

        System.out.println("Product added to cart!");
    }

    public static void viewCart(ArrayList<CartItem> cart) {
        System.out.println("\n========== SHOPPING CART ==========");

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        for (CartItem item : cart) {
            item.displayCartItem();
        }

        System.out.println("-----------------------------------");
        System.out.println("Cart Total: ₹" + calculateTotal(cart));
    }

    public static void updateQuantity(ArrayList<CartItem> cart, Scanner scanner) {
        viewCart(cart);

        if (cart.isEmpty()) return;

        System.out.print("\nEnter Product ID to update: ");
        int productId = Integer.parseInt(scanner.nextLine());
        CartItem item = findCartItem(cart, productId);

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.print("Enter new quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        if (quantity <= 0 || quantity > item.getProduct().getStock()) {
            System.out.println("Invalid or unavailable quantity.");
            return;
        }

        item.setQuantity(quantity);
        System.out.println("Quantity updated successfully!");
    }

    public static void removeFromCart(ArrayList<CartItem> cart, Scanner scanner) {
        viewCart(cart);

        if (cart.isEmpty()) return;

        System.out.print("\nEnter Product ID to remove: ");
        int productId = Integer.parseInt(scanner.nextLine());
        CartItem item = findCartItem(cart, productId);

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        cart.remove(item);
        System.out.println("Item removed from cart.");
    }

    public static double calculateTotal(ArrayList<CartItem> cart) {
        double total = 0;

        for (CartItem item : cart) {
            total += item.getTotalPrice();
        }

        return total;
    }

    private static Product findProduct(ArrayList<Product> products, int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) return product;
        }
        return null;
    }

    private static CartItem findCartItem(ArrayList<CartItem> cart, int productId) {
        for (CartItem item : cart) {
            if (item.getProduct().getProductId() == productId) return item;
        }
        return null;
    }
}
