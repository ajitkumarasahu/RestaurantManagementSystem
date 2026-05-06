package com.rms.controller;

import com.rms.model.Order;
import com.rms.model.OrderItem;
import com.rms.service.OrderService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    OrderService service;

    public OrderController() throws Exception {
        this.service = new OrderService();
    }

    // ================= PLACE ORDER =================
    @POST
    public Response placeOrder(Map<String, Object> data) {
        try {
            // ---------- BASIC VALIDATION ----------
            if (data.get("userId") == null || data.get("restaurantId") == null) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"UserId & RestaurantId required\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/orders\""
                    + "}"
                ).build();
            }

            if (data.get("totalAmount") == null) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Total amount required\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            int userId = (int) data.get("userId");
            int restaurantId = (int) data.get("restaurantId");
            double totalAmount = Double.parseDouble(data.get("totalAmount").toString());

            if (userId <= 0 || restaurantId <= 0) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Invalid User or Restaurant ID\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            if (totalAmount <= 0) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Total amount must be > 0\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            // ---------- CREATE ORDER ----------
            Order order = new Order(userId, restaurantId, totalAmount, "PENDING");

            List<Map<String,Object>> itemsMap = (List<Map<String,Object>>) data.get("items");

            if (itemsMap == null || itemsMap.isEmpty()) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Order items required\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            List<OrderItem> items = new java.util.ArrayList<>();

            for (Map<String,Object> m : itemsMap) {
                int foodId = (int) m.get("foodItemId");
                int qty = (int) m.get("quantity");
                double price = Double.parseDouble(m.get("price").toString());

                if (qty <= 0) {
                    return Response.status(400).entity(
                        "{"
                        + "\"message\":\"Quantity must be > 0\","
                        + "\"statusCode\":400"
                        + "}"
                    ).build();
                }

                items.add(new OrderItem(0, foodId, qty, price));
            }

            int orderId = service.placeOrder(order, items);

            return Response.status(201).entity(
                "{"
                + "\"message\":\"Order Placed Successfully\","
                + "\"orderId\":" + orderId + ","
                + "\"statusCode\":201,"
                + "\"path\":\"/api/orders\""
                + "}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Server Error\","
                + "\"statusCode\":500"
                + "}"
            ).build();
        }
    }

    // ================= GET ALL ORDERS =================
    @GET
    public Response getAllOrders() {
        try {
            List<Order> list = service.getAllOrders();

            if (list.isEmpty()) {
                return Response.status(404).entity(
                    "{"
                    + "\"message\":\"No Orders Found\","
                    + "\"statusCode\":404"
                    + "}"
                ).build();
            }

            return Response.ok(list).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Server Error\","
                + "\"statusCode\":500"
                + "}"
            ).build();
        }
    }

    // ================= GET ORDER BY ID =================
    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") int id) {
        try {
            if (id <= 0) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Invalid Order ID\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            Order order = service.getOrderById(id);

            if (order == null) {
                return Response.status(404).entity(
                    "{"
                    + "\"message\":\"Order Not Found\","
                    + "\"statusCode\":404"
                    + "}"
                ).build();
            }

            return Response.ok(order).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Server Error\","
                + "\"statusCode\":500"
                + "}"
            ).build();
        }
    }

    // ================= UPDATE ORDER STATUS =================
    @PUT
    @Path("/{id}")
    public Response updateStatus(@PathParam("id") int id, Map<String,String> body) {
        try {
            if (id <= 0) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Invalid Order ID\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            String status = body.get("status");

            if (status == null || status.isEmpty()) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Status required\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            boolean updated = service.updateStatus(id, status);

            if (!updated) {
                return Response.status(404).entity(
                    "{"
                    + "\"message\":\"Order Not Found\","
                    + "\"statusCode\":404"
                    + "}"
                ).build();
            }

            return Response.ok(
                "{"
                + "\"message\":\"Order Status Updated Successfully\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/orders\""
                + "}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Server Error\","
                + "\"statusCode\":500"
                + "}"
            ).build();
        }
    }

    // ================= DELETE ORDER =================
    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") int id) {
        try {
            if (id <= 0) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Invalid Order ID\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            boolean deleted = service.deleteOrder(id);

            if (!deleted) {
                return Response.status(404).entity(
                    "{"
                    + "\"message\":\"Order Not Found\","
                    + "\"statusCode\":404"
                    + "}"
                ).build();
            }

            return Response.ok(
                "{"
                + "\"message\":\"Order Deleted Successfully\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/orders\""
                + "}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Server Error\","
                + "\"statusCode\":500"
                + "}"
            ).build();
        }
    }
}