package com.rms.model;

public class Payment {

    private int id;
    private int orderId;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;
    private String paymentDate;

    public Payment() {}

    /**
     * Constructs a new Payment with the specified ID, order ID, amount, payment method, payment status, and payment date.
     *
     * @param id the ID of the payment
     * @param orderId the ID of the order associated with the payment
     * @param amount the amount of the payment
     * @param paymentMethod the method of payment (e.g., credit card, PayPal)
     * @param paymentStatus the status of the payment (e.g., pending, completed)
     * @param paymentDate the date of the payment
     */
    public Payment(int id, int orderId, double amount, String paymentMethod, String paymentStatus, String paymentDate) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
}