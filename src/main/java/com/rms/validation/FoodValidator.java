package com.rms.validation;

// Import model
import com.rms.model.FoodItem;

public class FoodValidator {

    // ================= VALIDATE CREATE =================
    public static String validateCreate(FoodItem f) {

        // Check request body
        if (f == null)
            return "Request body missing";

        // Validate name
        if (f.getName() == null || f.getName().trim().isEmpty())
            return "Food name is required";

        // Validate description
        if (f.getDescription() == null || f.getDescription().trim().isEmpty())
            return "Food description is required";

        // Validate price
        if (f.getPrice() <= 0)
            return "Food price must be greater than 0";

        // Validate category
        if (f.getCategoryId() <= 0)
            return "Category ID must be valid";

        // Validate restaurant
        if (f.getRestaurantId() <= 0)
            return "Restaurant ID must be valid";

        // All valid
        return null;
    }

    // ================= VALIDATE UPDATE =================
    public static String validateUpdate(FoodItem f) {

        // 🔥 Important fix: null check (missing earlier)
        if (f == null)
            return "Request body missing";

        // Validate ID
        if (f.getId() <= 0)
            return "Food ID required";

        // Validate name
        if (f.getName() == null || f.getName().trim().isEmpty())
            return "Food name required";

        // Validate price
        if (f.getPrice() <= 0)
            return "Food price must be greater than 0";

        return null;
    }

    // ================= VALIDATE ID =================
    public static String validateId(int id) {

        if (id <= 0)
            return "Invalid Food ID";

        return null;
    }
}