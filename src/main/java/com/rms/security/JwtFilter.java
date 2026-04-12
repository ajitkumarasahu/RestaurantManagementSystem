package com.rms.security;

// Intercepts incoming HTTP requests
import jakarta.ws.rs.container.*;

// Used to access HTTP headers like Authorization
import jakarta.ws.rs.core.HttpHeaders;

// Used to send HTTP responses (like 401 Unauthorized)
import jakarta.ws.rs.core.Response;

// Marks this class as a provider (auto-detected by Jersey)
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

// This filter runs BEFORE every request reaches your API
@Provider
public class JwtFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get requested API path (e.g., /api/auth/login, /api/secure)
        String path = requestContext.getUriInfo().getPath();

        // Skip authentication for auth endpoints (login & register)
        if (path.contains("auth"))
            return;

        // Get Authorization header
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if header is missing or doesn't start with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            // Stop request and return 401 Unauthorized
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                .entity(
                "{"
                + "\"message\":\"Token Missing\","
                + "\"statusCode\":401,"
                + "\"path\":\"" + requestContext.getUriInfo().getPath() + "\""
                + "}"
                ).build()
            );
            return;
        }

        // Extract token (remove "Bearer " prefix)
        String token = authHeader.substring("Bearer ".length());

        // Validate JWT token
        if (!JwtUtil.validateToken(token)) {

            // Stop request if token is invalid
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                .entity(
                "{"
                + "\"message\":\"Invalid Token\","
                + "\"statusCode\":401,"
                + "\"path\":\"" + requestContext.getUriInfo().getPath() + "\""
                + "}"
                ).build()
            );
        }
    }
}