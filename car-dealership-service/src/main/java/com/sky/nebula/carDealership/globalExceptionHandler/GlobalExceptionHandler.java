package com.sky.nebula.carDealership.globalExceptionHandler;

import com.sky.nebula.carDealership.exceptions.EmptyInputException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    EmptyInputException emptyInputException;

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {

            return new ResponseEntity<String> ("Incorrect car data provided", HttpStatusCode.valueOf(400));
        }

    }
