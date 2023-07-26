package com.sky.nebula.carDealership.exceptionHandlers;


import com.sky.nebula.carDealership.controllers.CarController;
import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.exceptions.InvalidDataException;
import com.sky.nebula.carDealership.exceptions.InvalidQueryParameterException;
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
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ExceptionHandlerTests {

    @Mock
    private CarRepository carRepository;
    CarController carController;

    @InjectMocks
    private CarService carService;
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        carService = new CarService(carRepository);
        carController = new CarController(carService);
    }


    @Test
    void handleCarAlreadyExistsExceptionTest() throws CarAlreadyExistsException {

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleCarAlreadyExists();
        Car car = new Car("BMW", "X5", 2022, 10000, 100000, "space grey");
        Mockito.when(carRepository.existsByBrandAndModelAndYearAndPriceAndMileageAndColour(
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getPrice(),
                car.getMileage(),
                car.getColour()
        )).thenReturn(true);

        HttpStatus expectedStatus = HttpStatus.CONFLICT;
        String key = "Description";
        String value = "Car already exists";

        Assertions.assertThrows(CarAlreadyExistsException.class, () ->
                carController.addCar(Collections.singletonList(car)));
        Assertions.assertEquals(expectedStatus, response.getStatusCode());
        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertTrue(response.getBody().containsValue(value));
    }

    @Test
    void addCarInvalidFieldDataReturns400AndErrorMessage() throws InvalidDataException {

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleInvalidInput();

        Car testCarMissingData = new Car("", "X5", null, 10000, 100000, "space grey");

        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        String key = "Description";
        String value = "Incorrect car data provided";

        Assertions.assertThrows(InvalidDataException.class, () ->
                carController.addCar(Collections.singletonList(testCarMissingData)));
        Assertions.assertEquals(expectedStatus, response.getStatusCode());
        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertTrue(response.getBody().containsValue(value));
    }

    @Test
    void handleMalformedAttributeNameTest() throws HttpMessageNotReadableException {

        String malformedJson = "this is not a json list of cars";

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleInvalidInput();

        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        String key = "Description";
        String value = "Incorrect car data provided";

        Assertions.assertThrows(InvalidDataException.class, () -> {
            carController.addCar(List.of(new Car(malformedJson)));
        });
        Assertions.assertEquals(expectedStatus, response.getStatusCode());
        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertTrue(response.getBody().containsValue(value));
    }

    @Test
    void handleInvalidQueryParameterTest() throws InvalidQueryParameterException {

        String invalidQueryParameter = "bent ley";

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleInvalidQueryParameter();

        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        String key = "Description";
        String value = "Incorrect query parameter provided";

        Assertions.assertThrows(InvalidQueryParameterException.class, () -> {
            carController.getBrand(invalidQueryParameter);
        });
        Assertions.assertEquals(expectedStatus, response.getStatusCode());
        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertTrue(response.getBody().containsValue(value));
    }
}
