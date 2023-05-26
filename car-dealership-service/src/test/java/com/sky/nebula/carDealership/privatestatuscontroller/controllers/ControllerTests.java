package com.sky.nebula.carDealership.privatestatuscontroller.controllers;

import com.sky.nebula.carDealership.controllers.CarController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ControllerTests {

    CarController carController = new CarController();


    @Test
    void addCarEndPointReturns201AndResponse() {

        ResponseEntity<Map<String, String>> response = carController.addCar();
        String key = "Description";
        String value = "Database Updated";

        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertTrue(response.getBody().containsValue(value));
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
