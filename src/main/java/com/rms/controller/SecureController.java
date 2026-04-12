package com.rms.controller;

// Import GET annotation for handling HTTP GET requests
import jakarta.ws.rs.GET;

// Defines the base path for this controller
import jakarta.ws.rs.Path;

// (Optional here, since you're returning String directly)
import jakarta.ws.rs.core.Response;

// Base URL: /api/secure
@Path("/secure")
public class SecureController {

    // Handles GET requests to /api/secure
    @GET
    public String securedAPI() {

        // JSON response as String
        return "{"
            + "\"message\":\"You accessed a SECURED API 🎉\","
            + "\"statusCode\":200,"
            + "\"path\":\"/api/secure\""
            + "}";
    }
}