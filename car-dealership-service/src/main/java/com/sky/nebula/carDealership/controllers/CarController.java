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

    @GetMapping("/admin/get")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("admin/brand={brand}")
    public ResponseEntity<List<Car>> getBrand(@PathVariable String brand) {
        List<Car> carsByBrand = carService.getBrand(brand);
        //return list of cars with the same brand
        return new ResponseEntity<>(carsByBrand, HttpStatus.OK);
    }

    @GetMapping("admin/model={model}")
    public ResponseEntity<List<Car>> getModel(@PathVariable String model) {
        List<Car> carsByModel = carService.getModel(model);
        return new ResponseEntity<>(carsByModel, HttpStatus.OK);
    }

    @GetMapping("admin/year={year}")
    public ResponseEntity<List<Car>> getYear(@PathVariable int year) {
        List<Car> carsByYear = carService.getYear(year);
        return new ResponseEntity<>(carsByYear, HttpStatus.OK);
    }

    @GetMapping("admin/price={price}")
    public ResponseEntity<List<Car>> getPrice(@PathVariable int price) {
        List<Car> carsByPrice = carService.getPrice(price);
        return new ResponseEntity<>(carsByPrice, HttpStatus.OK);
    }

    @GetMapping("admin/mileage={mileage}")
    public ResponseEntity<List<Car>> getMileage(@PathVariable int mileage) {
        List<Car> carsByMileage = carService.getMileage(mileage);
        return new ResponseEntity<>(carsByMileage, HttpStatus.OK);
    }

    @GetMapping("admin/colour={colour}")
    public ResponseEntity<List<Car>> getColour(@PathVariable String colour) {
        List<Car> carsByColour = carService.getColour(colour);
        return new ResponseEntity<>(carsByColour, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> deleteAllCars() {
        carService.deleteAllCars();
        return new ResponseEntity<String>("Database Cleared", HttpStatus.OK);
    }

    public void addCar(String malformedJsonRequest) {
    }

}
