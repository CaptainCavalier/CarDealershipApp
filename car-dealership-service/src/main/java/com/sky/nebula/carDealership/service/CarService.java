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

    public List<Car> addCar(List<Car> carList) {

        if (carList.size() == 0) {
            throw new EmptyInputException("400", "Car list is empty");
        }

        for (int i = 0; i < carList.size(); i++) {
//            Car carListEmptyValue = carList.get(i);
//            Car carListNotZero = carList.get(i);

            if (carList.get(i).getBrand().isEmpty()) {
                throw new EmptyInputException("400", "Incorrect car data provided");
            } else if (carList.get(i).getModel().isEmpty()) {
                throw new EmptyInputException("400", "Incorrect car data provided");
            } else if (carList.get(i).getYear().equals(0)) {
                throw new EmptyInputException("400", "Incorrect car data provided");
            } else if (carList.get(i).getPrice().equals(0)) {
                throw new EmptyInputException("400", "Incorrect car data provided");
            } else if (carList.get(i).getMileage().equals(0)) {
                throw new EmptyInputException("400", "Incorrect car data provided");
            } else if (carList.get(i).getColour().isEmpty()) {
                throw new EmptyInputException("400", "Incorrect car data provided");
            } else return carRepository.saveAll(carList);
        }
        return carRepository.saveAll(carList);
    }

        public List<Car> getAllCars() {

            return carRepository.findAll();
        }

        public void deleteAllCars() {
            carRepository.deleteAll();
        }
    }

