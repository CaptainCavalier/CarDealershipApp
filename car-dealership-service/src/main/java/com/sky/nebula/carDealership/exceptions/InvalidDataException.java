package com.sky.nebula.carDealership.exceptions;

import org.springframework.http.HttpStatus;


public class InvalidDataException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public InvalidDataException(String errorCode, HttpStatus httpStatus) {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String message) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public InvalidDataException(String errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }




}
