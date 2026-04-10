package com.rms.config;

// Provides request and response context for filtering
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;

// Interface for modifying HTTP responses
import jakarta.ws.rs.container.ContainerResponseFilter;

// Marks this class as a JAX-RS provider (auto-detected by Jersey)
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

// This class is automatically applied to all API responses
@Provider
public class CorsFilter implements ContainerResponseFilter {

    // This method runs for every request-response cycle
    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {

        // Allow requests from any origin (frontend, mobile apps, etc.)
        res.getHeaders().add("Access-Control-Allow-Origin", "*");

        // Allow specific headers in requests (important for JWT Authorization)
        res.getHeaders().add("Access-Control-Allow-Headers","origin, content-type, accept, authorization"); 
            
        // Allow credentials (cookies, authorization headers, etc.)
        res.getHeaders().add("Access-Control-Allow-Credentials", "true");

        // Allow these HTTP methods for cross-origin requests
        res.getHeaders().add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");
            
    }
}