package com.sky.nebula.carDealership.exceptionHandlers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.nebula.carDealership.controllers.CarController;
import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.exceptions.InvalidDataException;
import com.sky.nebula.carDealership.globalExceptionHandler.GlobalExceptionHandler;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import com.sky.nebula.carDealership.service.CarService;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;

import java.util.Collections;
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

//    @Test
//    void addCarInvalidAttributeDataReturns400AndErrorMessage() throws InvalidDataException {
//
//        String requestBody = "[{\"\":\"BMW\",\"model\":\"X6\",\"year\":2023,\"price\":18800,\"mileage\":10000,\"colour\":\"space grey\"}]";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
//
//        MockServerRequest.Builder restTemplate = null;
//        ResponseEntity<Map<String, String>> response = restTemplate.exchange("/cars/admin", HttpMethod.POST, request, new ParameterizedTypeReference<Map<String, String>>() {});
//
//        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
//        String key = "Description";
//        String value = "Incorrect car data provided";
//
//        Assertions.assertEquals(expectedStatus, response.getStatusCode());
//        Assertions.assertTrue(response.getBody().containsKey(key));
//        Assertions.assertTrue(response.getBody().containsValue(value));
//    }
//
//    @Test
//    void testInvalidAttributeExceptionHandling() throws InvalidDataException {
//        // Create a malformed car object
//        String malformedCarJson = "{ \"bra\": \"BMW\", \"model\": \"A5\", \"year\": 1900, \"price\": 10000, \"mileage\": 663000, \"colour\": \"sky blue\" }";
//        // Use the Jackson library to map the JSON to a Map object
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> malformedCarData;
//
//        // Mock the repository method call
//        Mockito.when(carRepository.existsByBrandAndModelAndYearAndPriceAndMileageAndColour(
//                (String) malformedCarData.get("bra"),
//                (String) malformedCarData.get("model"),
//                (Integer) malformedCarData.get("year"),
//                (Integer) malformedCarData.get("price"),
//                (Integer) malformedCarData.get("mileage"),
//                (String) malformedCarData.get("colour")
//        )).thenReturn(true);
//        // We expect the method call to throw an InvalidAtributeException
//        Assertions.assertThrows(InvalidDataException.class, () -> carController.addCar(Collections.singletonList(malformedCarData)));
//    }

}
