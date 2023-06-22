package com.sky.nebula.carDealership.privatestatuscontroller.controllers;


import com.sky.nebula.carDealership.controllers.CarController;
import com.sky.nebula.carDealership.model.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExceptionHandlerTests {

    CarController carController;

    @Test
    public void addCarMissingDataReturns400AndResponse() {
        ResponseEntity<Map<String, String>> response = carController.addCar(
                List.of(new Car(
                        "",
                        "X5",
                        2022,
                        80000,
                        10000,
                        "space grey")));

        String key = "Description";
        String value = "Incorrect car data provided";

        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertTrue(response.getBody().containsValue(value));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}