package com.sky.nebula.carDealership.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidIdentificationDataException extends InvalidDataException {
    public InvalidIdentificationDataException(String errorMessage, HttpStatus errorCode) {
        super(errorMessage, errorCode);

    }
}
