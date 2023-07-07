package com.sky.nebula.carDealership.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidAtributeException extends RuntimeException {

    private HttpStatus statusCode;

    public InvalidAtributeException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}

