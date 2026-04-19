package com.rms.validation;

// Import model
import com.rms.model.Category;

public class CategoryValidator {

    // ================= VALIDATE CREATE =================
    public static String validateCreate(Category c) {

        // Check if request body is null
        if (c == null)
            return "Request body missing";

        // Validate category name
        if (c.getName() == null || c.getName().trim().isEmpty())
            return "Category name is required";

        // Validate restaurant ID
        if (c.getRestaurantId() <= 0)
            return "Restaurant ID must be valid";

        // All good
        return null;
    }

    // ================= VALIDATE UPDATE =================
    public static String validateUpdate(Category c) {

        // ⚠️ Missing null check (important improvement)
        if (c == null)
            return "Request body missing";

        // Validate ID
        if (c.getId() <= 0)
            return "Category ID required";

        // Validate name
        if (c.getName() == null || c.getName().trim().isEmpty())
            return "Category name required";

        // Validate restaurant ID
        if (c.getRestaurantId() <= 0)
            return "Restaurant ID must be valid";

        return null;
    }

    // ================= VALIDATE ID =================
    public static String validateId(int id) {

        // Check if ID is valid
        if (id <= 0)
            return "Invalid Category ID";

        return null;
    }
}