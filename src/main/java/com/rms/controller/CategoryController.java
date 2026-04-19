package com.rms.controller;

// Import model, service, validator
import com.rms.model.Category;
import com.rms.service.CategoryService;
import com.rms.validation.CategoryValidator;

// JAX-RS imports
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

// Base URL: /api/categories
@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryController {

    // Service layer object
    CategoryService service;

    // Constructor (initializing service)
    public CategoryController() throws Exception {
        service = new CategoryService();
    }

    // ================= CREATE CATEGORY =================
    @POST
    public Response createCategory(Category c) {
        try {

            // Basic validation (name required)
            if (c.getName() == null || c.getName().isEmpty()) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Category name required\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/categories\""
                    + "}"
                ).build();
            }

            // Custom validator check
            String error = CategoryValidator.validateCreate(c);

            if (error != null) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/categories\""
                    + "}"
                ).build();
            }

            // Validate restaurant ID
            if (c.getRestaurantId() <= 0) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Restaurant ID required\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/categories\""
                    + "}"
                ).build();
            }

            // Save category
            service.create(c);

            return Response.status(201).entity(
                "{"
                + "\"message\":\"Category Created Successfully\","
                + "\"statusCode\":201,"
                + "\"path\":\"/api/categories\""
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

    // ================= GET CATEGORY BY RESTAURANT =================
    @GET
    @Path("/restaurant/{restaurantId}")
    public Response getByRestaurant(@PathParam("restaurantId") int restaurantId) {
        try {

            // Validate restaurant ID
            if (restaurantId <= 0) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Invalid restaurant id\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            // Fetch categories
            List<Category> list = service.getByRestaurant(restaurantId);

            // If empty list → 404
            if (list.isEmpty()) {
                return Response.status(404).entity(
                    "{"
                    + "\"message\":\"No Categories Found\","
                    + "\"statusCode\":404"
                    + "}"
                ).build();
            }

            // Return data
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

    // ================= UPDATE CATEGORY =================
    @PUT
    public Response updateCategory(Category c) {
        try {

            // Validate ID
            if (c.getId() <= 0) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Category ID required\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            // Validate name
            if (c.getName() == null || c.getName().isEmpty()) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Category name required\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            // 🔥 FIX: Validation should come BEFORE update
            String error = CategoryValidator.validateUpdate(c);

            if (error != null) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/categories\""
                    + "}"
                ).build();
            }

            // Update category
            service.update(c);

            return Response.ok(
                "{"
                + "\"message\":\"Category Update Successfully\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/categories\""
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

    // ================= DELETE CATEGORY =================
    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") int id) {
        try {

            // Validate ID
            if (id <= 0) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"Invalid Category ID\","
                    + "\"statusCode\":400"
                    + "}"
                ).build();
            }

            // Custom validator
            String error = CategoryValidator.validateId(id);

            if (error != null) {
                return Response.status(400).entity(
                    "{"
                    + "\"message\":\"" + error + "\","
                    + "\"statusCode\":400,"
                    + "\"path\":\"/api/categories\""
                    + "}"
                ).build();
            }

            // Delete category
            service.delete(id);

            return Response.ok(
                "{"
                + "\"message\":\"Category Deleted Successfully\","
                + "\"statusCode\":200,"
                + "\"path\":\"/api/categories\""
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