package com.rms.controller;

import com.rms.model.CartItem;
import com.rms.service.CartService;
import com.rms.validation.CartValidator;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartController {

    CartService service = new CartService();

    // Get/Create cart for user
    @GET
    @Path("/user/{userId}")
    public Response getCart(@PathParam("userId") int userId) {
        try {

            String error = CartValidator.validateUserId(userId);
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart/user\""
                    + "}"
                ).build();

            int cartId = service.getOrCreateCart(userId);
            return Response.ok(service.getItems(cartId)).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Internal Server Error\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/cart/user\""
                + "}"
            ).build();
        }
    }

    // Add item
    @POST
    public Response addItem(CartItem item) {
        try {

            String error = CartValidator.validateAddItem(item);
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart\""
                    + "}"
                ).build();

            service.addItem(item);

            return Response.status(201).entity(
                "{"
                + "\"message\":\"Item added to cart\","
                + "\"statusCode\":201,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Internal Server Error\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();
        }
    }

    // Update quantity
    @PUT
    @Path("/{id}/{qty}")
    public Response updateQty(@PathParam("id") int id, @PathParam("qty") int qty) {
        try {

            String error = CartValidator.validateUpdate(id, qty);
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart\""
                    + "}"
                ).build();

            service.updateQty(id, qty);

            return Response.status(200).entity(
                "{"
                + "\"message\":\"Quantity updated\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Internal Server Error\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();
        }
    }

    // Delete item
    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") int id) {
        try {

            String error = CartValidator.validateDelete(id);
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart\""
                    + "}"
                ).build();

            service.deleteItem(id);

            return Response.status(200).entity(
                "{"
                + "\"message\":\"Item removed\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Internal Server Error\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();
        }
    }

    // Clear cart
    @DELETE
    @Path("/clear/{cartId}")
    public Response clearCart(@PathParam("cartId") int cartId) {
        try {

            String error = CartValidator.validateCartId(cartId);
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart\""
                    + "}"
                ).build();

            service.clearCart(cartId);

            return Response.status(200).entity(
                "{"
                + "\"message\":\"Cart cleared\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Internal Server Error\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();
        }
    }
}