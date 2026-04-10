package com.rms.model;

public class Order {

    private int id;
    private int userId;
    private double totalAmount;
    private String status;
    private String createdAt;

    public Order() {}

    /**
     * Constructs a new Order with the specified ID, user ID, total amount, status, and creation date.
     *
     * @param id the ID of the order
     * @param userId the ID of the user who placed the order
     * @param totalAmount the total amount of the order
     * @param status the status of the order (e.g., "pending", "completed", "cancelled")
     * @param createdAt the date and time when the order was created
     */
    public Order(int id, int userId, double totalAmount, String status, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}