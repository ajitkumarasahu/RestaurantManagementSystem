package com.rms.controller;

import jakarta.ws.rs.Consumes;
// Annotation for HTTP GET method
import jakarta.ws.rs.GET;

// Defines the base path for this controller
import jakarta.ws.rs.Path;

// Specifies the response format (JSON here)
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// Java time classes for date & time handling
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Base URL: /api/test (because @ApplicationPath("/api") + @Path("/test"))
@Path("/test")
public class TestController {

    // Handles HTTP GET requests
    @GET
    // Accept JSON input (not really needed for GET, but added for consistency)
    @Consumes(MediaType.APPLICATION_JSON)

    // Response will be returned in JSON format
    @Produces(MediaType.APPLICATION_JSON)
    public String testAPI() {

        // Get current date and time from system
        LocalDateTime now = LocalDateTime.now();

        // Formatter for date (YYYY-MM-DD)
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Formatter for time (HH:MM:SS)
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Formatter for full date & time
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Get current day name (MONDAY, TUESDAY, etc.)
        String dayName = now.getDayOfWeek().toString();

        // Example Odia month name (static value)
        String odiaMonthName = "ଏପ୍ରିଲ"; // April in Odia

        // Manually building JSON response as a String
        String response = 
        
        "{"
            + "\"status\":\"SUCCESS\","
            + "\"statusCode\":200,"
            + "\"message\":\"Restaurant Management System API is working! Successfully\","
            + "\"path\":\"/api/test\","
            + "\"date\":\"" + now.format(dateFormatter) + "\","
            + "\"time\":\"" + now.format(timeFormatter) + "\","
            + "\"serverDateTime\":\"" + now.format(dateTimeFormatter) + "\","
            + "\"dayName\":\"" + dayName + "\","
            + "\"monthOdia\":\"" + odiaMonthName + "\""
        + "}";

        // Return JSON response
        return response;
    }
}