package com.rms.model;

public class Payment {

    private int id;
    private int orderId;
    private String paymentMethod;
    private String paymentStatus;
    private String transactionId;
    private String createdAt;

    public Payment(){}

    public Payment(int orderId,String paymentMethod,String paymentStatus,String transactionId){
        this.orderId=orderId;
        this.paymentMethod=paymentMethod;
        this.paymentStatus=paymentStatus;
        this.transactionId=transactionId;
    }

    public int getId(){ return id; }
    public void setId(int id){ this.id=id; }

    public int getOrderId(){ return orderId; }
    public void setOrderId(int orderId){ this.orderId=orderId; }

    public String getPaymentMethod(){ return paymentMethod; }
    public void setPaymentMethod(String paymentMethod){ this.paymentMethod=paymentMethod; }

    public String getPaymentStatus(){ return paymentStatus; }
    public void setPaymentStatus(String paymentStatus){ this.paymentStatus=paymentStatus; }

    public String getTransactionId(){ return transactionId; }
    public void setTransactionId(String transactionId){ this.transactionId=transactionId; }

    public String getCreatedAt(){ return createdAt; }
    public void setCreatedAt(String createdAt){ this.createdAt=createdAt; }
}