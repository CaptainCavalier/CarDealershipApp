package com.sky.nebula.carDealership.service;

import com.sky.nebula.carDealership.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
}
