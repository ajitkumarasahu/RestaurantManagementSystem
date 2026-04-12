package com.rms.security;

// Intercepts incoming HTTP requests
import jakarta.ws.rs.container.*;

// Used to read HTTP headers
import jakarta.ws.rs.core.HttpHeaders;

// Used to send custom HTTP responses
import jakarta.ws.rs.core.Response;

// Marks this as a global filter (auto-detected by Jersey)
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class RoleFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get request path (e.g., api/admin/users)
        String path = requestContext.getUriInfo().getPath();

        // Get HTTP method (GET, POST, etc.) - not used here but useful
        String method = requestContext.getMethod();

        // Get Authorization header
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // If no token is provided → allow request (can be improved later)
        if (authHeader == null) return;

        // Extract token from "Bearer <token>"
        String token = authHeader.substring("Bearer ".length());

        // Extract role from JWT token
        String role = JwtUtil.extractRole(token);

        // =========================
        // ADMIN ACCESS CONTROL
        // =========================
        // If API path contains "admin" but user is not ADMIN → block access
        if (path.contains("admin") && !role.equals("ADMIN")) {

            requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN)
                .entity(
                    "{"
                    + "\"message\":\"Admin Access Required\","
                    + "\"statusCode\":403,"
                    + "\"path\":\"" + path + "\""
                    + "}"
                ).build()
            );
        }

        // =========================
        // OWNER ACCESS CONTROL
        // =========================
        // If API path contains "owner" but user is not OWNER → block access
        if (path.contains("owner") && !role.equals("OWNER")) {

            requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN)
                .entity(
                    "{"
                    + "\"message\":\"Owner Access Required\","
                    + "\"statusCode\":403,"
                    + "\"path\":\"" + path + "\""
                    + "}"
                ).build()
            );
        }
    }
}