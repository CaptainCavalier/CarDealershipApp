package com.sky.nebula.carDealership.privatestatuscontroller.controllers;

import com.sky.nebula.carDealership.controllers.CarController;
import com.sky.nebula.carDealership.globalExceptionHandler.GlobalExceptionHandler;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class ControllerTests {

    @InjectMocks
    CarController carController;

    @Mock
    CarService carService;


    // Need to test car is actually added in the addCar method
    @Test
    void addCarEndPointReturns201AndResponse() {

        ResponseEntity<Map<String, String>> response = carController.addCar(
                List.of(new Car(
                "BMW",
                "X5",
                2022,
                80000,
                10000,
                "space grey")));

        String key = "Description";
        String value = "Database Updated";

        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertTrue(response.getBody().containsValue(value));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getAllCarsEndpointReturns200AndResponse() {
        // Arrange
        Car car1 = new Car("BMW", "X5", 2022, 80000, 10000, "space grey");
        Car car2 = new Car("BMW", "X6", 2023, 100000, 1000, "sky blue");
        List<Car> carList =  List.of(car1, car2);

        Mockito.when(carService.getAllCars()).thenReturn(carList);

        // Act
        ResponseEntity<List<Car>> response = carController.getAllCars();

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(carList, response.getBody());

        System.out.println(response);

    }

}
