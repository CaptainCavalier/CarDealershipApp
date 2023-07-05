package com.sky.nebula.carDealership.controllers;


import com.sky.nebula.carDealership.exceptions.InvalidDataException;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import com.sky.nebula.carDealership.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/cars")
public class CarController {

    CarRepository carRepository;

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/admin")
    public ResponseEntity<Map<String, String>> addCar(@Valid @RequestBody List<Car> carList) {
        for (Car car : carList) {
            if ((car.getBrand() == null) || car.getBrand().isEmpty() ||
                    (car.getModel() == null) || car.getModel().isEmpty() ||
                    (car.getYear() == null) || (car.getYear().toString().length() != 4) ||
                    (car.getPrice() == null) || car.getPrice().equals(0) ||
                    (car.getMileage() == null) || (car.getMileage() == (0)) ||
                    (car.getColour() == null) || car.getColour().isEmpty()) {
                throw new InvalidDataException(String.valueOf(Map.of("Description", "Incorrect car data provided")), HttpStatus.BAD_REQUEST);
            }
        }
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
