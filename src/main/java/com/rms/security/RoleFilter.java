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

        // If role is missing or invalid → block access
        if (role == null) {
            requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN)
                .entity(
                    "{"
                    + "\"message\":\"Invalid token - role missing\","
                    + "\"statusCode\":403,"
                    + "\"path\":\"" + path + "\""
                    + "}"
                ).build()
            );
        }

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

        // Protect menu + restaurant APIs
        if (path.startsWith("categories") || path.startsWith("foods") || path.startsWith("owner")) {

            //String authHeader = request.getHeaderString("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                    .entity(
                        "{"
                        + "\"message\":\"Token missing\","
                        + "\"statusCode\":401,"
                        + "\"path\":\"" + path + "\""
                        + "}"
                    ).build()
                );
            }
        }
        
        ///String token = authHeader.substring("Bearer ".length());

        if (!JwtUtil.validateToken(token)) {
            requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN)
                .entity(
                    "{"
                    + "\"message\":\"Invalid token\","
                    + "\"statusCode\":401,"
                    + "\"path\":\"" + path + "\""
                    + "}"
                ).build()
            );
        }

        String roles = JwtUtil.getRole(token);

        // 🔥 Allow only OWNER & ADMIN
        if (path.startsWith("restaurants") || path.startsWith("categories") || path.startsWith("foods")) {

            if (!(roles.equals("OWNER") || roles.equals("ADMIN"))) {
                requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                    .entity(
                        "{"
                        + "\"message\":\"Access denied OWNER or ADMIN only  \","
                        + "\"statusCode\":403,"
                        + "\"path\":\"" + path + "\""
                        + "}"
                    ).build()
                );
            }
        }

        // 🛒 Allow only CUSTOMER APIs
        if (path.startsWith("cart") || path.startsWith("orders") || path.startsWith("payments")) {

            if (!role.equals("CUSTOMER")) {
                requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN).entity(
                    "{"
                    + "\"message\":\"Access denied CUSTOMER only\","
                    + "\"statusCode\":403,"
                    + "\"path\":\"" + path + "\""
                    + "}"
                ).build());
            }
        }

        // Public APIs (no token required)
        if (path.startsWith("auth") || path.startsWith("test") || path.startsWith("secure")) 
            return;
    }
}