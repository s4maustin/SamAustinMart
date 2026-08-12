package modules;

import models.CartItem;
import models.Order;
import models.Product;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckoutModule {
    private static int nextOrderId = 1;

    public static Order checkout(int buyerId,
                                 ArrayList<CartItem> cart,
                                 Scanner scanner) {
        System.out.println("\n========== CHECKOUT ==========");

        if (cart.isEmpty()) {
            System.out.println("Cannot checkout. Cart is empty.");
            return null;
        }

        CartModule.viewCart(cart);

        System.out.print("\nDo you want to confirm the order? (Y/N): ");
        String choice = scanner.nextLine();

        if (!choice.equalsIgnoreCase("Y")) {
            System.out.println("Checkout cancelled.");
            return null;
        }

        for (CartItem item : cart) {
            Product product = item.getProduct();

            if (item.getQuantity() > product.getStock()) {
                System.out.println("Insufficient stock for " + product.getName());
                return null;
            }
        }

        double total = CartModule.calculateTotal(cart);
        Order order = new Order(nextOrderId++, buyerId, cart, total);

        for (CartItem item : cart) {
            Product product = item.getProduct();
            product.setStock(product.getStock() - item.getQuantity());
        }

        cart.clear();

        System.out.println("\nOrder placed successfully!");
        order.displayOrder();

        return order;
    }
}
