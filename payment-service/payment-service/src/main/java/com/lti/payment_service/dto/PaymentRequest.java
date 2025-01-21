package com.lti.payment_service.dto;

public class PaymentRequest {
    private String bill_id;
    private double amount;

    public PaymentRequest() {
    }

    public PaymentRequest(String bill_id, double amount) {
        this.bill_id = bill_id;
        this.amount = amount;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
