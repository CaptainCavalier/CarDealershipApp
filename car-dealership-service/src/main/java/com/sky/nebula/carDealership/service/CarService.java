package com.sky.nebula.carDealership.service;

import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.exceptions.InvalidDataException;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CarService {

    private CarRepository carRepository;


    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> addCar(List<Car> carList) {

        List<Car> newCars = new ArrayList<>();

        for (Car car : carList) {
            if (car.getBrand().isEmpty() ||
                    car.getModel().isEmpty() ||
                    car.getYear().toString().length() != 4 ||
                    car.getPrice().equals(0) ||
                    car.getMileage().equals(0) ||
                    car.getColour().isEmpty()) {

                throw new InvalidDataException("400", "Incorrect car data provided");
            } else if (carRepository.existsByBrandAndModelAndYearAndPriceAndMileageAndColour(
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getPrice(),
                    car.getMileage(),
                    car.getColour())) {

                throw new CarAlreadyExistsException("409", "Car already exists");
            } else {
                newCars.add(car);
            }
        }

        return carRepository.saveAll(newCars);
    }


    public List<Car> getAllCars() {

            return carRepository.findAll();
        }

        public void deleteAllCars() {
            carRepository.deleteAll();
        }
    }

