package com.rms.controller;

import com.rms.model.User;
import com.rms.security.JwtUtil;
import com.rms.service.AuthService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    // SERVICE LAYER CALL FOR BUSINESS LOGIC
    AuthService authService = new AuthService();

    // REGISTER API
    @POST
    @Path("/register")
    public Response register(User user) {

        // VALIDATION
        String result = authService.register(user);

        if (result.equals("User Registered Successfully")){

            return Response.status(Response.Status.CREATED)
            .entity(
                "{"
                + "\"message\":\"" + result + "\","
                + "\"statusCode\":201,"
                + "\"path\":\"/api/auth/register\""
                +"}"
            ).build();
        } else{
            return Response.status(Response.Status.BAD_REQUEST)
            .entity(
                "{"
                + "\"message\":\"" + result + "\","
                + "\"statusCode\":400,"
                + "\"path\":\"/api/auth/register\""
                +"}"
            ).build();
        }
    }

    // LOGIN API
    @POST
    @Path("/login")
    public Response login(User loginRequest) {

        User user = authService.login(loginRequest.getEmail(),loginRequest.getPassword());
                                      
        if (user == null){
            return Response.status(Response.Status.UNAUTHORIZED)
            .entity(
                "{"
                +"\"message\":\"Invalid email or password\""
                + "\"statusCode\":401,"
                + "\"path\":\"/api/auth/login\"}"
                + "}"
            ).build();
        } else {
            // Generate JWT
            String token = JwtUtil.generateToken(user.getEmail(), user.getRole());
            return Response.ok()
            .entity(
                "{"
                + "\"message\":\"Login successful\","
                + "\"statusCode\":200,"
                 + "\"token\":\"" + token + "\"," 
                + "\"path\":\"/api/auth/login\","
                + "\"user\":{"
                    + "\"id\":" + user.getId() + ","
                    + "\"name\":\"" + user.getName() + "\","
                    + "\"email\":\"" + user.getEmail() + "\","
                    + "\"role\":\"" + user.getRole() + "\""
                + "}"
                + "}"
            ).build();
        }   
    }
}