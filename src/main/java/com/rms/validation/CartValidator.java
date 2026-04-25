package com.rms.validation;

import com.rms.model.CartItem;

public class CartValidator {

    // Add Item Validation
    public static String validateAddItem(CartItem item){

        if(item == null)
            return "Request body missing";

        if(item.getCartId() <= 0)
            return "Cart ID must be valid";

        if(item.getFoodItemId() <= 0)
            return "Food Item ID must be valid";

        if(item.getQuantity() <= 0)
            return "Quantity must be greater than 0";

        if(item.getQuantity() > 50)
            return "Max quantity allowed is 50";

        return null;
    }

    // Update Quantity Validation
    public static String validateUpdate(int id, int qty){

        if(id <= 0)
            return "Cart Item ID must be valid";

        if(qty <= 0)
            return "Quantity must be greater than 0";

        if(qty > 50)
            return "Max quantity allowed is 50";

        return null;
    }

    // Delete Item Validation
    public static String validateDelete(int id){

        if(id <= 0)
            return "Cart Item ID must be valid";

        return null;
    }

    // Clear Cart Validation
    public static String validateCartId(int cartId){

        if(cartId <= 0)
            return "Cart ID must be valid";

        return null;
    }

    // User Cart Validation
    public static String validateUserId(int userId){

        if(userId <= 0)
            return "User ID must be valid";

        return null;
    }
}