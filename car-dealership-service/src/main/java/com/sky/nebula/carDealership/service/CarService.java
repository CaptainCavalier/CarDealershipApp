package com.sky.nebula.carDealership.service;

import com.sky.nebula.carDealership.exceptions.EmptyInputException;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void addCar(List<Car> carList) {

        for (int i = 0; i < carList.size(); i++) {
            Car carListEmptyValue = carList.get(i);
            Car carListNotZero = carList.get(i);

            if (carList.isEmpty() || carList.size() == 0) {
                throw new EmptyInputException("400", "Incorrect car data provided");
            }
        };

        carRepository.saveAll(carList);
    }

    public List<Car> getAllCars() {

        return carRepository.findAll();
    }

    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}
