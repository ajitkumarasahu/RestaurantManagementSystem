package com.rms.model;

public class OrderItem {

    private int id;
    private int orderId;
    private int foodId;
    private int quantity;
    private double price;

    public OrderItem() {}

    /**
     * Constructs a new OrderItem with the specified ID, order ID, food ID, quantity, and price.
     *
     * @param id the ID of the order item
     * @param orderId the ID of the order to which this item belongs
     * @param foodId the ID of the food item
     * @param quantity the quantity of the food item in the order
     * @param price the price of the food item in the order
     */
    public OrderItem(int id, int orderId, int foodId, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}