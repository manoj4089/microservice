package com.lti.payment_service.model;


public class Payment {

    private Long id;
    private Long billId;
    private double amount;
    private String status;

    public Payment() {
    }

    public Payment(Long id, Long billId, double amount, String status) {
        this.id = id;
        this.billId = billId;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
