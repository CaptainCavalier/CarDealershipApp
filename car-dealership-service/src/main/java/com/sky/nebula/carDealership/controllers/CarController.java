package com.sky.nebula.carDealership.controllers;

import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/admin")
    public ResponseEntity<Map<String, String>> addCar(@RequestBody List<Car> carList) {

        carService.addCar(carList);
        return new ResponseEntity<>(Map.of("Description", "Database Updated"), HttpStatus.CREATED);
    }

}
