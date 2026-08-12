package models;

public class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private int sellerId;

    public Product(int productId, String name, String description,
                   double price, int stock, String category, int sellerId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.sellerId = sellerId;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public int getSellerId() { return sellerId; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategory(String category) { this.category = category; }

    public void displayProduct() {
        System.out.println("--------------------------------");
        System.out.println("Product ID  : " + productId);
        System.out.println("Name        : " + name);
        System.out.println("Description : " + description);
        System.out.println("Price       : ₹" + price);
        System.out.println("Stock       : " + stock);
        System.out.println("Category    : " + category);
        System.out.println("Seller ID   : " + sellerId);
        System.out.println("--------------------------------");
    }
}
