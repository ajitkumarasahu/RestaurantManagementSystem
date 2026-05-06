package com.rms.validation;

import com.rms.model.Payment;

public class PaymentValidator {

    public static String validate(Payment p){

        if(p.getOrderId() <= 0)
            return "OrderId required";

        if(p.getPaymentMethod()==null || p.getPaymentMethod().isEmpty())
            return "Payment method required";

        if(!p.getPaymentStatus().equals("COMPLETED") &&
           !p.getPaymentStatus().equals("FAILED") &&
           !p.getPaymentStatus().equals("PENDING"))
            return "Invalid payment status";

        if(p.getTransactionId()==null || p.getTransactionId().isEmpty())
            return "Transaction id required";

        return null;
    }
}