package com.rms.controller;

// Import GET annotation for handling HTTP GET requests
import jakarta.ws.rs.GET;

// Defines the base path for this controller
import jakarta.ws.rs.Path;

// Base URL: /api/secure
@Path("/secure")
public class SecureController {

    // Handles GET requests to /api/secure
    @GET
    public String securedAPI() {

        // Manually building a JSON response as a String
        String response = 

        "{"
            + "\"message\":\"You accessed a SECURED API 🎉\","
            + "\"statusCode\":200,"
            + "\"path\":\"/api/secure\""
        + "}";

        // Return the JSON response as a String
        return response;
    }
}