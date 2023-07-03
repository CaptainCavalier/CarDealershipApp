package com.sky.nebula.carDealership.service;

import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.exceptions.InvalidDataException;
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

        Car car = new Car();

        if (carList.size() == 0) {
            throw new InvalidDataException("400", "Car list is empty");
        }

//        Car existingCar = carRepository.findByAll(car.getBrand(), car.getModel(), car.getYear(), car.getPrice(), car.getMileage(), car.getColour());
//        if (existingCar != null) {
//            throw new CarAlreadyExistsException("409", "Car already exists in the database");
//        }

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getBrand().isEmpty()) {
                throw new InvalidDataException("400", "Incorrect car data provided");
            } else if (carList.get(i).getModel().isEmpty()) {
                throw new InvalidDataException("400", "Incorrect car data provided");
            } else if (carList.get(i).getYear().equals(0)) {
                throw new InvalidDataException("400", "Incorrect car data provided");
            } else if (carList.get(i).getPrice().equals(0)) {
                throw new InvalidDataException("400", "Incorrect car data provided");
            } else if (carList.get(i).getMileage().equals(0)) {
                throw new InvalidDataException("400", "Incorrect car data provided");
            } else if (carList.get(i).getColour().isEmpty()) {
                throw new InvalidDataException("400", "Incorrect car data provided");
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

