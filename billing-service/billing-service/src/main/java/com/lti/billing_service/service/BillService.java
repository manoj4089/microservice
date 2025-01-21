package com.lti.billing_service.service;

import com.lti.billing_service.dto.OrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

@Service
public class BillService {

    public double generateBill(@RequestBody OrderRequest orderRequest){
        List<HashMap<String, Object>> orderLineItems = orderRequest.getOrderLineItems();

//        double total_price = 0;
//        for(HashMap<String, Object> orderLine : orderLineItems){
//            double price = (double)orderLine.get("price");
//            int quantity = (int) orderLine.get("quantity");
//            total_price = total_price + (price * quantity);
//        }

        double total_price = orderLineItems.stream()
                .mapToDouble(orderLine -> (double)orderLine.get("price") * (int) orderLine.get("quantity"))
                .sum();


        System.out.println("***************HELLO PRICE*********" + total_price);
        return total_price;
    }
}
