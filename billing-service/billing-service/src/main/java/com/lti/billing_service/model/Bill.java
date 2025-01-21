package com.lti.billing_service.model;

public class Bill {
    private String billId;
    private double amount;

    public Bill() {
    }

    public Bill(String billId, double amount) {
        this.billId = billId;
        this.amount = amount;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
