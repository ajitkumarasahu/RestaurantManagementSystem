package com.rms.model;

public class Cart {

    private int id;
    private int userId;

    public Cart() {}

    /**
     * Constructs a new Cart with the specified ID and user ID.
     *
     * @param id the ID of the cart
     * @param userId the ID of the user who owns the cart
     */
    public Cart(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}