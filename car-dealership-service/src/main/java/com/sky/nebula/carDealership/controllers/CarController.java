package com.sky.nebula.carDealership.controllers;

import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import com.sky.nebula.carDealership.service.CarService;
import com.sky.nebula.carDealership.validators.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {

    CarRepository carRepository;

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/admin")
    public ResponseEntity<Map<String, String>> addCar(@Validated @RequestBody List<Car> carList) {
        for (Car car : carList) {
            if (car.getBrand() == null || car.getBrand().isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brand cannot be empty");
        } //The above loop is changing the status code to 400 when "brand" is empty. but doesn't return the description


        carService.addCar(carList);
        return new ResponseEntity<>(Map.of("Description", "Database Updated"), HttpStatus.CREATED);
    }


    @GetMapping("/admin")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> deleteAllCars() {
        carService.deleteAllCars();
        return new ResponseEntity<String>("Database Cleared", HttpStatus.OK);
    }


}
