package com.sky.nebula.carDealership.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidQueryParameterException extends InvalidDataException {
    public InvalidQueryParameterException(String errorCode, HttpStatus errorMessage) {
        super(errorCode, errorMessage);
    }
}
