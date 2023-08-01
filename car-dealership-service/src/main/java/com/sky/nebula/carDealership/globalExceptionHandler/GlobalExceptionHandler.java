package com.sky.nebula.carDealership.globalExceptionHandler;

import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.exceptions.InvalidDataException;
import com.sky.nebula.carDealership.exceptions.InvalidQueryParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ExceptionHandler({InvalidQueryParameterException.class, MissingServletRequestParameterException.class})
    public ResponseEntity handleInvalidQueryParameter() {
        return new ResponseEntity<>(Map.of("Description","Incorrect query parameter provided"), HttpStatus.BAD_REQUEST);
    }

}
