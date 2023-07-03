package com.sky.nebula.carDealership.globalExceptionHandler;

import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.exceptions.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarAlreadyExistsException.class)
    public ResponseEntity<String> handleCarAlreadyExists() {
        return new ResponseEntity<String> ("Car already exists", HttpStatus.valueOf(409));
    }


    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleValidInput() {
        return new ResponseEntity<String> ("Incorrect car data provided", HttpStatus.BAD_REQUEST);
    }

}
