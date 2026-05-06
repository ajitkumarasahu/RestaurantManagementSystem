package com.rms.service;

import com.rms.dao.PaymentDAO;
import com.rms.dao.OrderDAO;
import com.rms.model.Payment;

public class PaymentService {

    PaymentDAO paymentDAO = new PaymentDAO();
    OrderDAO orderDAO = new OrderDAO();

    public void pay(Payment payment) throws Exception {

        // Save payment
        paymentDAO.createPayment(payment);

        // If payment success → mark order delivered
        if(payment.getPaymentStatus().equals("COMPLETED")){
            orderDAO.updateStatus(payment.getOrderId(),"DELIVERED");
        }
    }

    public Payment getPayment(int orderId) throws Exception {
        return paymentDAO.getByOrderId(orderId);
    }
}