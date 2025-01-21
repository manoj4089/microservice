package com.lti.shopping_service.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
}
