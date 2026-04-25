package com.rms.model;

public class CartItem {

    private int id;
    private int cartId;
    private int foodItemId; 
    private int quantity;

    public CartItem() {}
    
    /**
     * Constructs a new CartItem with the specified ID, cart ID, food ID, and quantity.
     *
     * @param id the ID of the cart item
     * @param cartId the ID of the cart to which this item belongs
     * @param foodId the ID of the food item
     * @param quantity the quantity of the food item in the cart
     */
    public CartItem(int id, int cartId, int foodItemId, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.foodItemId = foodItemId;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getFoodItemId() { return foodItemId; }
    public void setFoodItemId(int foodItemId) { this.foodItemId = foodItemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}