package com.sky.nebula.carDealership.exceptions;

public class CarAlreadyExistsException extends InvalidDataException{
    public CarAlreadyExistsException(String errorCode, String errorMessage) {
        super(errorCode);
    }
}
