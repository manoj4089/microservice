package com.lti.billing_service.dto;

import java.util.HashMap;
import java.util.List;

public class OrderRequest {
    private String orderNumber;
    private List<HashMap<String, Object>> orderLineItems;

    public OrderRequest() {
    }

    public OrderRequest(String orderNumber, List<HashMap<String, Object>> orderLineItems) {
        this.orderNumber = orderNumber;
        this.orderLineItems = orderLineItems;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<HashMap<String, Object>> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<HashMap<String, Object>> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}
