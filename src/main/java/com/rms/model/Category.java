package com.rms.model;

public class Category {

    private int id;
    private String name;
    private int restaurantId;

    public Category() {}

    /**
     * Constructs a new Category with the specified ID, name, and restaurant ID.
     *
     * @param id the ID of the category
     * @param name the name of the category
     * @param restaurantId the ID of the restaurant to which the category belongs
     */
    public Category(int id, String name, int restaurantId) {
        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
}