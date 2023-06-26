package com.sky.nebula.carDealership.exceptionHandlers;


import com.sky.nebula.carDealership.controllers.CarController;
import com.sky.nebula.carDealership.exceptions.EmptyInputException;
import com.sky.nebula.carDealership.globalExceptionHandler.GlobalExceptionHandler;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import com.sky.nebula.carDealership.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ExceptionHandlerTests {

    @Mock
    private CarRepository carRepository;
    CarController carController;

    @InjectMocks
    private CarService carService;
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void addCarMissingDataReturns400AndResponse() {

        ResponseEntity<Map<String, String>> carAdded = carController.addCar(
                List.of(new Car(
                        "",
                        "X5",
                        2022,
                        80000,
                        10000,
                        "space grey")));

    }

}
