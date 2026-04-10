package com.rms.model;

public class FoodItem {

    private int id;
    private String name;
    private String description;
    private double price;
    private int categoryId;
    private int restaurantId;

    public FoodItem() {}
    
    /**
     * Constructs a new FoodItem with the specified ID, name, description, price, category ID, and restaurant ID.
     *
     * @param id the ID of the food item
     * @param name the name of the food item
     * @param description a brief description of the food item
     * @param price the price of the food item
     * @param categoryId the ID of the category to which the food item belongs
     * @param restaurantId the ID of the restaurant that offers the food item
     */
    public FoodItem(int id, String name, String description, double price, int categoryId, int restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
}