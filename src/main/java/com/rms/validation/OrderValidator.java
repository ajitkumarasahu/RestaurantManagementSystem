package com.rms.validation;

import com.rms.model.Order;
import com.rms.model.OrderItem;

import java.util.List;

public class OrderValidator {

    // ================= CREATE ORDER VALIDATION =================
    public static String validateCreate(Order order, List<OrderItem> items) {

        if (order == null)
            return "Order body is missing";

        if (order.getUserId() <= 0)
            return "Valid userId required";

        if (order.getRestaurantId() <= 0)
            return "Valid restaurantId required";

        if (order.getTotalAmount() <= 0)
            return "Total amount must be greater than 0";

        if (items == null || items.isEmpty())
            return "Order must contain at least one item";

        for (OrderItem item : items) {
            String error = validateOrderItem(item);
            if (error != null) return error;
        }

        return null;
    }

    // ================= UPDATE STATUS VALIDATION =================
    public static String validateStatus(String status) {

        if (status == null || status.isEmpty())
            return "Status is required";

        switch (status) {
            case "PENDING":
            case "ACCEPTED":
            case "PREPARING":
            case "OUT_FOR_DELIVERY":
            case "DELIVERED":
                return null;
            default:
                return "Invalid order status";
        }
    }

    // ================= ORDER ITEM VALIDATION =================
    public static String validateOrderItem(OrderItem item) {

        if (item.getFoodItemId() <= 0)
            return "Food item id required";

        if (item.getQuantity() <= 0)
            return "Quantity must be greater than 0";

        if (item.getPrice() <= 0)
            return "Item price must be greater than 0";

        return null;
    }

    // ================= DELETE VALIDATION =================
    public static String validateId(int id) {
        if (id <= 0)
            return "Invalid ID";
        return null;
    }
}