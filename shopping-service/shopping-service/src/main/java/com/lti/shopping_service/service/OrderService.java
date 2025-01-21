package com.lti.shopping_service.service;

import com.lti.shopping_service.exception.OrderNotFoundException;
import com.lti.shopping_service.model.Order;
import com.lti.shopping_service.model.OrderLineItems;
import com.lti.shopping_service.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired private OrderRepo orderRepo;

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

    public Order addOrderLineToOrder(Long order_id, OrderLineItems orderLineItems){
        if(orderRepo.existsById(order_id)){
            Order order = orderRepo.findById(order_id).get();
            List<OrderLineItems> orderLineItemsList = order.getOrderLineItems();
            orderLineItemsList.add((orderLineItems));
            return orderRepo.save(order);

        }else{
            throw new OrderNotFoundException("Order not found with id: " + order_id);
        }
    }

    public Order addOrder(List<OrderLineItems> orderLineItems){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItems(orderLineItems);
        return orderRepo.save(order);
    }

    public Order getOrderById(Long id){
        if(orderRepo.existsById(id)){
            Order orderReq = orderRepo.findById(id).get();
            return orderReq;
        }
        else{
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
    }

}
