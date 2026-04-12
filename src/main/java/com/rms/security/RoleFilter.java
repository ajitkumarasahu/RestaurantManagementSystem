package com.rms.security;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class RoleFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) return;

        String token = authHeader.substring("Bearer ".length());
        String role = JwtUtil.extractRole(token);

        // ADMIN APIs
        if (path.contains("admin") && !role.equals("ADMIN")) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Admin Access Required").build());       
        }

        // OWNER APIs
        if (path.contains("owner") && !role.equals("OWNER")) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Owner Access Required").build());          
        }
    }
}