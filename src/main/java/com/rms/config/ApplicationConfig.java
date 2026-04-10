package com.rms.config;

// Import annotation to define base URI for REST APIs
import jakarta.ws.rs.ApplicationPath;

// Base class for configuring JAX-RS applications
import jakarta.ws.rs.core.Application;

// This annotation sets the base URL path for all REST endpoints
// All APIs will be available under: http://localhost:8080/your-app/api
@ApplicationPath("/api")

// This class activates JAX-RS (Jersey) in your application
public class ApplicationConfig extends Application {
    
    // No additional configuration is required here
    // Jersey will automatically scan for REST resources (based on setup)
}