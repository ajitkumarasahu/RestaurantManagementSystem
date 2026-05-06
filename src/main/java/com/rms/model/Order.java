package com.rms.model;

import java.time.LocalDateTime;

public class Order {

    private int id;
    private int userId;
    private int restaurantId;
    private double totalAmount;
    private String status;
    private LocalDateTime createdAt;

    public Order() {}

    /**
     * Constructs a new Order with the specified user ID, restaurant ID, total amount, and status.
     * 
     * @param userId 
     * @param restaurantId
     * @param totalAmount
     * @param status
     */

    public Order(int userId, int restaurantId, double totalAmount, String status) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}