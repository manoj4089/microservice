package com.lti.payment_service.controller;

import com.lti.payment_service.dto.PaymentRequest;
import com.lti.payment_service.model.Payment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @PostMapping("/api/payment/processPayment")
    public String processPayment(@RequestBody PaymentRequest paymentRequest){
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setStatus("SUCCESS");

        return "Making payment...\n" +
                "Payment Status: " + payment.getStatus() +
                "\nPayment made of Rs. " + payment.getAmount();
    }
}
