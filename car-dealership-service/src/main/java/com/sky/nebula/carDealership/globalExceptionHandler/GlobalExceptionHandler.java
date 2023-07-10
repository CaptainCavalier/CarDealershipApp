package com.sky.nebula.carDealership.globalExceptionHandler;

import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.exceptions.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    @ExceptionHandler(CarAlreadyExistsException.class)
    public ResponseEntity handleCarAlreadyExists() {
        return new ResponseEntity<>(Map.of("Description", "Car already exists"), HttpStatus.CONFLICT);
    }


    @ExceptionHandler({InvalidDataException.class, HttpMessageNotReadableException.class})
    public ResponseEntity handleInvalidInput() {
        return new ResponseEntity<>(Map.of("Description","Incorrect car data provided"), HttpStatus.BAD_REQUEST);
    }

}
