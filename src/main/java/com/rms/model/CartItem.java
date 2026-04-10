package com.rms.model;

public class CartItem {

    private int id;
    private int cartId;
    private int foodId;
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
    public CartItem(int id, int cartId, int foodId, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}