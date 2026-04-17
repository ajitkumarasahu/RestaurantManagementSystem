package com.rms.validation;

import com.rms.model.FoodItem;

public class FoodValidator {

    public static String validateCreate(FoodItem f) {

        if (f == null)
            return "Request body missing";

        if (f.getName() == null || f.getName().trim().isEmpty())
            return "Food name is required";

        if (f.getDescription() == null || f.getDescription().trim().isEmpty())
            return "Food description is required";

        if (f.getPrice() <= 0)
            return "Food price must be greater than 0";

        if (f.getCategoryId() <= 0)
            return "Category ID must be valid";

        if (f.getRestaurantId() <= 0)
            return "Restaurant ID must be valid";

        return null;
    }

    public static String validateUpdate(FoodItem f) {

        if (f.getId() <= 0)
            return "Food ID required";

        if (f.getName() == null || f.getName().trim().isEmpty())
            return "Food name required";

        if (f.getPrice() <= 0)
            return "Food price must be greater than 0";

        return null;
    }

    public static String validateId(int id) {
        if (id <= 0)
            return "Invalid Food ID";
        return null;
    }
}