package com.sky.nebula.carDealership.privatestatuscontroller;

import com.sky.nebula.carDealership.controllers.PrivateStatusController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PrivateEndpointTests {
    PrivateStatusController privateStatusController = new PrivateStatusController();
    @Test
    void privateStatusEndpointReturnsOkAnd200() {

        ResponseEntity<String> response = privateStatusController.getPrivateStatus();
        Assertions.assertEquals("OK", response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
