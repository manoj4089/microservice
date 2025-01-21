package com.lti.billing_service.controller;

import com.lti.billing_service.dto.OrderRequest;
import com.lti.billing_service.model.Bill;
import com.lti.billing_service.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class BillController {

    @Autowired private BillService billService;
    @Autowired private RestTemplate restTemplate;

    @PostMapping("/api/billing/generateBill")
    public String generateBill(@RequestBody OrderRequest orderRequest){
        double total_price =  billService.generateBill(orderRequest);

        Bill bill = new Bill();
        bill.setBillId(UUID.randomUUID().toString());
        bill.setAmount(total_price);

        String paymentResponse = restTemplate.postForObject(
//                "http://localhost:55522/api/payment/processPayment",
                //added load balancing - will not need to go specifically to url
                //localhost:55522 url for this request, can go to ANY healthy instance
                //of payment-service
                "http://payment-service/api/payment/processPayment",
                bill,
                String.class
        );
        return "Bill Generated Successfully! \n" + paymentResponse;
    }
}
