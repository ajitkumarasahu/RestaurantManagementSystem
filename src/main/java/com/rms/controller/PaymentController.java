package com.rms.controller;

import com.rms.model.Payment;
import com.rms.service.PaymentService;
import com.rms.validation.PaymentValidator;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentController {

    PaymentService service = new PaymentService();

    // 💳 MAKE PAYMENT
    @POST
    public Response pay(Payment payment){
        try{

            String error = PaymentValidator.validate(payment);
            if(error!=null){
                return Response.status(400).entity(
                    "{"
                    +"\"message\":\""+error+"\","
                    +"\"statusCode\":400,"
                    +"\"path\":\"/api/payments\""
                    +"}"
                ).build();
            }

            service.pay(payment);

            return Response.status(201).entity(
                "{"
                +"\"message\":\"Payment Successful\","
                +"\"statusCode\":201,"
                +"\"path\":\"/api/payments\""
                +"}"
            ).build();

        }catch(Exception e){
            return Response.status(500).entity(
                "{"
                +"\"message\":\"Server Error\","
                +"\"statusCode\":500"
                +"}"
            ).build();
        }
    }

    // 🔍 GET PAYMENT BY ORDER
    @GET
    @Path("/{orderId}")
    public Response getPayment(@PathParam("orderId") int orderId){
        try{
            Payment p = service.getPayment(orderId);

            if(p==null)
                return Response.status(404).entity(
                "{ \"message\":\"Payment not found\" }").build();

            return Response.ok(p).build();

        }catch(Exception e){
            return Response.status(500).build();
        }
    }
}