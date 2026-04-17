package com.rms.controller;

import com.rms.model.FoodItem;
import com.rms.service.FoodService;
import com.rms.validation.FoodValidator;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/foods")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FoodController {

    FoodService service;

    public FoodController() throws Exception {
        this.service = new FoodService();
    }

    // ================= ADD FOOD =================
    @POST
    public Response addFood(FoodItem food) {
        try {

            if (food.getName() == null || food.getName().isEmpty()) {
                return Response.status(400).entity(
                    "{ \"message\":\"Food name required\",\"statusCode\":400,\"path\":\"/api/foods\"}"
                ).build();
            }

            if (food.getPrice() <= 0) {
                return Response.status(400).entity(
                    "{ \"message\":\"Price must be greater than 0\",\"statusCode\":400}"
                ).build();
            }

            if (food.getRestaurantId() <= 0 || food.getCategoryId() <= 0) {
                return Response.status(400).entity(
                        "{ \"message\":\"Restaurant & Category required\",\"statusCode\":400}"
                ).build();
            }

            String error = FoodValidator.validateCreate(food);
            if(error != null){
                return Response.status(400).entity(
                    "{ \"message\":\""+error+"\",\"statusCode\":400,\"path\":\"/api/foods\"}"
                ).build();
            }

            service.create(food);

            return Response.status(201).entity(
                "{ \"message\":\"Food Added Successfully\",\"statusCode\":201,\"path\":\"/api/foods\"}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{ \"message\":\"Server Error\",\"statusCode\":500}"
            ).build();
        }
    }

    // ================= GET FOOD BY RESTAURANT =================
    @GET
    @Path("/restaurant/{restaurantId}")
    public Response getFoods(@PathParam("restaurantId") int restaurantId) {
        try {

            if (restaurantId <= 0) {
                return Response.status(400).entity(
                    "{ \"message\":\"Invalid restaurant id\",\"statusCode\":400}"
                ).build();
            }

            List<FoodItem> list = service.getByRestaurant(restaurantId);

            if (list.isEmpty()) {
                return Response.status(404).entity(
                    "{ \"message\":\"No Food Items Found\",\"statusCode\":404}"
                ).build();
            }

            return Response.ok(list).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{ \"message\":\"Server Error\",\"statusCode\":500}"
            ).build();
        }
    }

    // ================= UPDATE FOOD =================
    @PUT
    public Response updateFood(FoodItem food) {
        try {

            if (food.getId() <= 0) {
                return Response.status(400).entity(
                    "{ \"message\":\"Food ID required\",\"statusCode\":400}"
                ).build();
            }

            if (food.getName() == null || food.getName().isEmpty()) {
                return Response.status(400).entity(
                    "{ \"message\":\"Food name required\",\"statusCode\":400}"
                ).build();
            }

            if (food.getPrice() <= 0) {
                return Response.status(400).entity(
                    "{ \"message\":\"Invalid price\",\"statusCode\":400}"
                ).build();
            }

            String error = FoodValidator.validateUpdate(food);
            if(error != null){
                return Response.status(400).entity(
                    "{ \"message\":\""+error+"\",\"statusCode\":400,\"path\":\"/api/foods\"}"
                ).build();
            }

            service.update(food);

            return Response.ok(
                "{ \"message\":\"Food Updated Successfully\",\"statusCode\":200,\"path\":\"/api/foods\"}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                    "{ \"message\":\"Server Error\",\"statusCode\":500}"
            ).build();
        }
    }

    // ================= DELETE FOOD =================
    @DELETE
    @Path("/{id}")
    public Response deleteFood(@PathParam("id") int id) {
        try {
            if (id <= 0) {
                return Response.status(400).entity(
                    "{ \"message\":\"Invalid Food ID\",\"statusCode\":400}"
                ).build();
            }

            service.delete(id);  // Assuming a service.delete method exists

            return Response.ok(
                "{ \"message\":\"Food Deleted Successfully\",\"statusCode\":200,\"path\":\"/api/foods\"}"
            ).build();

        } catch (Exception e) {
            return Response.status(500).entity(
                "{ \"message\":\"Server Error\",\"statusCode\":500}"
            ).build();
        }
    }
}