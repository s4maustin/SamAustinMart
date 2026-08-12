package models;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private int buyerId;
    private ArrayList<CartItem> items;
    private double totalAmount;
    private String status;

    public Order(int orderId, int buyerId, ArrayList<CartItem> items, double totalAmount) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.items = new ArrayList<>(items);
        this.totalAmount = totalAmount;
        this.status = "CONFIRMED";
    }

    public int getOrderId() { return orderId; }
    public int getBuyerId() { return buyerId; }
    public ArrayList<CartItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }

    public void displayOrder() {
        System.out.println("\n========== ORDER ==========");
        System.out.println("Order ID    : " + orderId);
        System.out.println("Buyer ID    : " + buyerId);
        System.out.println("Status      : " + status);
        System.out.println("\nItems:");

        for (CartItem item : items) {
            item.displayCartItem();
        }

        System.out.println("----------------------------");
        System.out.println("Total Amount: ₹" + totalAmount);
        System.out.println("============================");
    }
}
