package com.rms.security;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class JwtFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String path = requestContext.getUriInfo().getPath();

        // Skip auth endpoints
        if (path.contains("auth"))
            return;

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token Missing").build());       
            return;
        }

        String token = authHeader.substring("Bearer ".length());

        if (!JwtUtil.validateToken(token)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Invalid Token").build());          
        }
    }
}