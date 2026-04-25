package com.rms.controller; // Package declaration for controller layer

// Import required classes
import com.rms.model.CartItem;        // Model representing cart item
import com.rms.service.CartService;  // Service layer for business logic
import com.rms.validation.CartValidator; // Validation utility

import jakarta.ws.rs.*;              // JAX-RS annotations
import jakarta.ws.rs.core.*;         // Response and MediaType classes

// Base path for all endpoints in this controller
@Path("/cart")

// Specifies response format as JSON
@Produces(MediaType.APPLICATION_JSON)

// Specifies request format as JSON
@Consumes(MediaType.APPLICATION_JSON)
public class CartController {

    // Create instance of service layer
    CartService service = new CartService();

    // ============================
    // GET CART FOR USER
    // ============================
    @GET // HTTP GET method
    @Path("/user/{userId}") // URL: /cart/user/{userId}
    public Response getCart(@PathParam("userId") int userId) {
        try {

            // Validate userId
            String error = CartValidator.validateUserId(userId);

            // If validation fails, return 400 response
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\"," // Error message
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart/user\""
                    + "}"
                ).build();

            // Get existing cart or create new cart
            int cartId = service.getOrCreateCart(userId);

            // Fetch items from cart and return
            return Response.ok(service.getItems(cartId)).build();

        } catch (Exception e) {
            // Handle unexpected errors
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Internal Server Error\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/cart/user\""
                + "}"
            ).build();
        }
    }

    // ============================
    // ADD ITEM TO CART
    // ============================
    @POST // HTTP POST method
    public Response addItem(CartItem item) {
        try {

            // Validate incoming cart item
            String error = CartValidator.validateAddItem(item);

            // Return error if validation fails
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart\""
                    + "}"
                ).build();

            // Add item to cart via service
            service.addItem(item);

            // Return success response
            return Response.status(201).entity(
                "{"
                + "\"message\":\"Item added to cart\","
                + "\"statusCode\":201,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();

        } catch (Exception e) {
            // Handle server error
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Internal Server Error\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();
        }
    }

    // ============================
    // UPDATE ITEM QUANTITY
    // ============================
    @PUT // HTTP PUT method
    @Path("/{id}/{qty}") // URL: /cart/{id}/{qty}
    public Response updateQty(@PathParam("id") int id, @PathParam("qty") int qty) {
        try {

            // Validate id and quantity
            String error = CartValidator.validateUpdate(id, qty);

            // Return validation error
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart\""
                    + "}"
                ).build();

            // Update quantity in service layer
            service.updateQty(id, qty);

            // Success response
            return Response.status(200).entity(
                "{"
                + "\"message\":\"Quantity updated\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();

        } catch (Exception e) {
            // Handle server errors
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Internal Server Error\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();
        }
    }

    // ============================
    // DELETE ITEM FROM CART
    // ============================
    @DELETE // HTTP DELETE method
    @Path("/{id}") // URL: /cart/{id}
    public Response deleteItem(@PathParam("id") int id) {
        try {

            // Validate item id
            String error = CartValidator.validateDelete(id);

            // Return error if invalid
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart\""
                    + "}"
                ).build();

            // Remove item from cart
            service.deleteItem(id);

            // Success response
            return Response.status(200).entity(
                "{"
                + "\"message\":\"Item removed\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();

        } catch (Exception e) {
            // Handle server error
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Internal Server Error\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();
        }
    }

    // ============================
    // CLEAR ENTIRE CART
    // ============================
    @DELETE // HTTP DELETE method
    @Path("/clear/{cartId}") // URL: /cart/clear/{cartId}
    public Response clearCart(@PathParam("cartId") int cartId) {
        try {

            // Validate cart id
            String error = CartValidator.validateCartId(cartId);

            // Return error if invalid
            if (error != null)
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/cart\""
                    + "}"
                ).build();

            // Clear all items from cart
            service.clearCart(cartId);

            // Success response
            return Response.status(200).entity(
                "{"
                + "\"message\":\"Cart cleared\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/cart\""
                + "}"
            ).build();

        } catch (Exception e) {
            // Handle server error
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