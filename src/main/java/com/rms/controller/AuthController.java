package com.rms.controller;

// Import model, JWT utility, and service layer
import com.rms.model.User;
import com.rms.security.JwtUtil;
import com.rms.service.AuthService;

// JAX-RS imports
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// Base path: /api/auth
@Path("/auth")

// Accept JSON input
@Consumes(MediaType.APPLICATION_JSON)

// Return JSON output
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    // Service layer handles business logic (DB operations, validation, etc.)
    AuthService authService = new AuthService();

    // =========================
    // REGISTER API
    // =========================
    @POST
    @Path("/register")
    public Response register(User user) {

        String result =  authService.register(user);

        // Call service to register user
        //String result = authService.register(user);

        // If registration successful
        if (result.equals("User Registered Successfully")) {

            return Response.status(Response.Status.CREATED) // HTTP 201
            .entity(
                "{"
                + "\"message\":\"" + result + "\","
                + "\"statusCode\":201,"
                + "\"path\":\"/api/auth/register\""
                + "}"
            ).build();

        } else {
            // If validation fails or user already exists
            return Response.status(Response.Status.BAD_REQUEST) // HTTP 400
            .entity(
                "{"
                + "\"message\":\"" + result + "\","
                + "\"statusCode\":400,"
                + "\"path\":\"/api/auth/register\""
                + "}"
            ).build();
        }
    }

    // =========================
    // LOGIN API
    // =========================
    @POST
    @Path("/login")
    public Response login(User loginRequest) {


        // Validate user credentials using service layer
        User user = authService.login(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        );

        // If user not found or invalid credentials
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED) // HTTP 401
            .entity(
                "{"
                + "\"message\":\"Invalid email or password\","
                + "\"statusCode\":401,"
                + "\"path\":\"/api/auth/login\""
                + "}"
            ).build();

        } else {
            // Generate JWT token using email & role
            String token = JwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
            );

            // Return success response with token and user details
            return Response.ok() // HTTP 200
            .entity(
                "{"
                + "\"message\":\"Login successful\","
                + "\"statusCode\":200,"
                + "\"token\":\"" + token + "\","
                + "\"path\":\"/api/auth/login\","
                + "\"user\":{"
                    + "\"id\":" + user.getId() + ","
                    + "\"name\":\"" + user.getName() + "\","
                    + "\"email\":\"" + user.getEmail() + "\","
                    + "\"role\":\"" + user.getRole() + "\""
                + "}"
                + "}"
            ).build();
        }
    }
}