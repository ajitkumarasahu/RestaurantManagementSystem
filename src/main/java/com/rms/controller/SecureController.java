package com.rms.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/secure")
public class SecureController {

    @GET
    public String securedAPI() {
        return "You accessed a SECURED API 🎉";
    }
}