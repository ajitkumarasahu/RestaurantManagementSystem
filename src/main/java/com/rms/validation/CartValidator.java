package com.rms.validation; // Package for validation logic

// Import CartItem model
import com.rms.model.CartItem;

public class CartValidator {

    // =====================================
    // VALIDATE ADD ITEM REQUEST
    // =====================================
    public static String validateAddItem(CartItem item){

        // Check if request body is null
        if(item == null)
            return "Request body missing";

        // Validate cart ID (must be positive)
        if(item.getCartId() <= 0)
            return "Cart ID must be valid";

        // Validate food item ID (must be positive)
        if(item.getFoodItemId() <= 0)
            return "Food Item ID must be valid";

        // Validate quantity (must be greater than 0)
        if(item.getQuantity() <= 0)
            return "Quantity must be greater than 0";

        // Limit maximum quantity to prevent abuse
        if(item.getQuantity() > 50)
            return "Max quantity allowed is 50";

        // If all validations pass → return null (no error)
        return null;
    }

    // =====================================
    // VALIDATE UPDATE QUANTITY REQUEST
    // =====================================
    public static String validateUpdate(int id, int qty){

        // Validate cart item ID
        if(id <= 0)
            return "Cart Item ID must be valid";

        // Validate quantity
        if(qty <= 0)
            return "Quantity must be greater than 0";

        // Max quantity check
        if(qty > 50)
            return "Max quantity allowed is 50";

        // No errors
        return null;
    }

    // =====================================
    // VALIDATE DELETE ITEM REQUEST
    // =====================================
    public static String validateDelete(int id){

        // Validate cart item ID
        if(id <= 0)
            return "Cart Item ID must be valid";

        // No errors
        return null;
    }

    // =====================================
    // VALIDATE CLEAR CART REQUEST
    // =====================================
    public static String validateCartId(int cartId){

        // Validate cart ID
        if(cartId <= 0)
            return "Cart ID must be valid";

        // No errors
        return null;
    }

    // =====================================
    // VALIDATE USER ID
    // =====================================
    public static String validateUserId(int userId){

        // Validate user ID
        if(userId <= 0)
            return "User ID must be valid";

        // No errors
        return null;
    }
}