package com.rms.validation;

// Import Restaurant model
import com.rms.model.Restaurant;

public class RestaurantValidator {

    // Static method to validate restaurant data
    public static String validateRestaurant(Restaurant r) {

        // Check if name is null or empty
        if (r.getName() == null || r.getName().isEmpty())
            return "Restaurant name is required";

        // Check if address is null or empty
        if (r.getAddress() == null || r.getAddress().isEmpty())
            return "Restaurant address is required";

        // Check if phone is null or empty
        if (r.getPhone() == null || r.getPhone().isEmpty())
            return "Restaurant phone is required";

        // Check if ownerId is valid (must be > 0)
        if (r.getOwnerId() <= 0)
            return "Owner ID is invalid";

        // If all validations pass → return null (means valid)
        return null;
    }
}