package com.rms.controller;

// Import model, service, and validator
import com.rms.model.Restaurant;
import com.rms.service.RestaurantService;
import com.rms.validation.RestaurantValidator;

// JAX-RS imports
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

// Base path: /api/owner/restaurants
@Path("/owner/restaurants")

// Accept & return JSON
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantController {

    // Service layer for business logic
    RestaurantService service = new RestaurantService();

    // =========================
    // CREATE SINGLE RESTAURANT
    // =========================
    @POST
    public Response createRestaurant(Restaurant r, @Context UriInfo uriInfo) {

        // Validate input
        String error = RestaurantValidator.validateRestaurant(r);

        if (error != null) {
            return Response.status(400).entity(
                "{"
                + "\"message\":\"" + error + "\","
                + "\"statusCode\":400,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        }

        // Save restaurant
        boolean saved = service.createRestaurant(r);

        if (saved) {
            return Response.status(201).entity(
                "{"
                + "\"message\":\"Restaurant created successfully\","
                + "\"statusCode\":201,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        } else {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Restaurant creation failed\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        }
    }

    // =========================
    // CREATE MULTIPLE RESTAURANTS
    // =========================
    @POST
    @Path("/bulk")
    public Response createRestaurant(List<Restaurant> restaurants) {

        // Validate each restaurant
        for (Restaurant r : restaurants) {
            String error = RestaurantValidator.validateRestaurant(r);

            if (error != null) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/owner/restaurants\""
                    + "}"
                ).build();
            }
        }

        boolean saved = service.createRestaurant(restaurants);

        if (saved) {
            return Response.status(201).entity(
                "{"
                + "\"message\":\"Restaurants created successfully\","
                + "\"statusCode\":201,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        } else {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Restaurant creation failed\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        }
    }

    // =========================
    // GET ALL RESTAURANTS
    // =========================
    @GET
    public Response getAllRestaurants() {

        // Fetch all restaurants
        List<Restaurant> list = service.getAllRestaurants();

        // Return list directly (auto JSON conversion)
        return Response.ok(list).build();
    }

    // =========================
    // GET RESTAURANT BY ID
    // =========================
    @GET
    @Path("/{id}")
    public Response getRestaurant(@PathParam("id") int id) {

        Restaurant r = service.getRestaurantById(id);

        if (r == null) {
            return Response.status(404).entity(
                "{"
                + "\"message\":\"Restaurant not found\","
                + "\"statusCode\":404,"
                + "\"path\":\"/api/owner/restaurants/" + id + "\""
                + "}"
            ).build();
        }

        return Response.ok(r).build();
    }

    // =========================
    // UPDATE SINGLE RESTAURANT
    // =========================
    @PUT
    @Path("/{id}")
    public Response updateRestaurant(@PathParam("id") int id, Restaurant r) {

        // Set ID from path
        r.setId(id);

        boolean updated = service.updateRestaurant(r);

        if (updated) {
            return Response.ok(
                "{"
                + "\"message\":\"Restaurant updated successfully\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/owner/restaurants/" + id + "\""
                + "}"
            ).build();
        } else {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Restaurant update failed\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/owner/restaurants/" + id + "\""
                + "}"
            ).build();
        }
    }

    // =========================
    // UPDATE MULTIPLE RESTAURANTS
    // =========================
    @PUT
    @Path("/bulk")
    public Response updateRestaurant(List<Restaurant> restaurants) {

        // Validate all
        for (Restaurant r : restaurants) {
            String error = RestaurantValidator.validateRestaurant(r);

            if (error != null) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/owner/restaurants\""
                    + "}"
                ).build();
            }
        }

        boolean updated = service.updateRestaurant(restaurants);

        if (updated) {
            return Response.ok(
                "{"
                + "\"message\":\"Restaurants updated successfully\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        } else {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Restaurant update failed\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        }
    }

    // =========================
    // DELETE SINGLE RESTAURANT
    // =========================
    @DELETE
    @Path("/{id}")
    public Response deleteRestaurant(@PathParam("id") int id) {

        boolean deleted = service.deleteRestaurant(id);

        if (deleted) {
            return Response.ok(
                "{"
                + "\"message\":\"Restaurant deleted successfully\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/owner/restaurants/" + id + "\""
                + "}"
            ).build();
        } else {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Restaurant delete failed\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/owner/restaurants/" + id + "\""
                + "}"
            ).build();
        }
    }

    // =========================
    // DELETE MULTIPLE RESTAURANTS
    // =========================
    @DELETE
    @Path("/bulk")
    public Response deleteRestaurant(List<Integer> ids) {

        // Validate IDs list
        if (ids == null || ids.isEmpty()) {
            return Response.status(400).entity(
                "{"
                + "\"message\":\"IDs list cannot be empty\","
                + "\"statusCode\":400,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        }

        boolean deleted = service.deleteRestaurant(ids);

        if (deleted) {
            return Response.ok(
                "{"
                + "\"message\":\"Restaurants deleted successfully\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        } else {
            return Response.status(500).entity(
                "{"
                + "\"message\":\"Restaurant delete failed\","
                + "\"statusCode\":500,"
                + "\"path\":\"/api/owner/restaurants\""
                + "}"
            ).build();
        }
    }
}