package com.sky.nebula.carDealership.controllers;


import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import com.sky.nebula.carDealership.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/admin/post")
    public ResponseEntity<Map<String, String>> addCar(@Valid @RequestBody List<Car> car) {

        carService.addCar(car);
        return new ResponseEntity<>(Map.of("Description", "Database Updated"), HttpStatus.CREATED);
    }

    @GetMapping("admin/brand={brand}")
    public ResponseEntity<List<Car>> getBrand(@PathVariable String brand) {
        List<Car> carsByBrand = carService.getBrand(brand);
        //return list of cars with the same brand
        return new ResponseEntity<>(carsByBrand, HttpStatus.OK);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> deleteAllCars() {
        carService.deleteAllCars();
        return new ResponseEntity<String>("Database Cleared", HttpStatus.OK);
    }

    public void addCar(String malformedJsonRequest) {
    }

}
