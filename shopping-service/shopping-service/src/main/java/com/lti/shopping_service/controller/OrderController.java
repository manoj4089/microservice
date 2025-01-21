package com.lti.shopping_service.controller;

import com.lti.shopping_service.exception.OrderNotFoundException;
import com.lti.shopping_service.model.Order;
import com.lti.shopping_service.model.OrderLineItems;
import com.lti.shopping_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private RestTemplate restTemplate;

    @PostMapping("/api/order/{id}")
    public ResponseEntity<?> addOrderListItemToOrder(@PathVariable Long id, @RequestBody OrderLineItems orderLineItems){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrderLineToOrder(id, orderLineItems));
        }
        catch(OrderNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/api/order")
    public ResponseEntity<?> addOrder(@RequestBody HashMap<String, List<OrderLineItems>> map){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(map.get("orderLineItems")));
    }

    @GetMapping("/api/order")
    public ResponseEntity<?> getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/api/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
        }catch(OrderNotFoundException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @CircuitBreaker(name= "BillingBreaker", fallbackMethod = "fallbackForBilling")
    @PostMapping("/api/order/placeOrder/{id}")
    public String placeOrder(@PathVariable Long id){
        try{
            Order orderReq = orderService.getOrderById(id);
            String billResponse = restTemplate.postForObject(
                    "http://billing-service/api/billing/generateBill",
                    orderReq,
                    String.class
            );
            //            String billResponse
            return "Order placed successfully! \n" + billResponse;
        }
        catch(OrderNotFoundException e){
            return e.getMessage();
        }
    }

    public String fallbackForBilling(Throwable t){
        //service is down so fallback is executed
        return "Billing Service is down. Please try again later";
    }

}
