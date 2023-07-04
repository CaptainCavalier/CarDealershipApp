package com.sky.nebula.carDealership.exceptionHandlers;


import com.sky.nebula.carDealership.controllers.CarController;

import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.globalExceptionHandler.GlobalExceptionHandler;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import com.sky.nebula.carDealership.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ExceptionHandlerTests {

    @Mock
    private CarRepository carRepository;
    CarController carController;

    @InjectMocks
    private CarService carService;
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    Car testCar = new Car("BMW", "X5", 2022, 10000, 100000, "space grey");

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        carService = new CarService(carRepository);
        carController = new CarController(carService);
    }

    @Test
    void handleCarAlreadyExistsExceptionTest() {

        Car car = new Car("BMW", "X5", 2022, 10000, 100000, "space grey");
        ResponseEntity<Map<String, String>> response = carController.addCar(Collections.singletonList(car));
        Mockito.when(carRepository.existsByBrandAndModelAndYearAndPriceAndMileageAndColour(
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getPrice(),
                car.getMileage(),
                car.getColour()
        )).thenReturn(true);

        String key = "Description";
        String value = "Car already exists";

        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertFalse(response.getBody().containsValue(value));

        Assertions.assertThrows(CarAlreadyExistsException.class, () ->
                carController.addCar(Collections.singletonList(car)));
    }


    @Test
    void handleValidInputExceptionTest(){

//        Calls the handleValidInput method and captures the response
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleValidInput();

        // declares and initializes the expected status code and message
        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        String key = "Description";
        String value = "Incorrect car data provided";

        //        Validates the status code response
        Assertions.assertEquals(expectedStatus, response.getStatusCode());

        //        validates the body of the response
        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertTrue(response.getBody().containsValue(value));
    }

}
