package com.sky.nebula.carDealership.globalExceptionHandler;

import com.sky.nebula.carDealership.exceptions.EmptyInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    EmptyInputException emptyInputException;

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput() {

            return new ResponseEntity<String> ("Incorrect car data provided", HttpStatus.BAD_REQUEST);
        }

    }
