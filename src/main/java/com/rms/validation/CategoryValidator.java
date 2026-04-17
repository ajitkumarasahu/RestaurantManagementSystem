package com.rms.validation;

import com.rms.model.Category;

public class CategoryValidator {

    public static String validateCreate(Category c) {

        if (c == null)
            return "Request body missing";

        if (c.getName() == null || c.getName().trim().isEmpty())
            return "Category name is required";

        if (c.getRestaurantId() <= 0)
            return "Restaurant ID must be valid";

        return null; // valid
    }

    public static String validateUpdate(Category c) {

        if (c.getId() <= 0)
            return "Category ID required";

        if (c.getName() == null || c.getName().trim().isEmpty())
            return "Category name required";

        if (c.getRestaurantId() <= 0)
            return "Restaurant ID must be valid";

        return null;
    }

    public static String validateId(int id) {
        if (id <= 0)
            return "Invalid Category ID";
        return null;
    }
}