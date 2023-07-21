package com.sky.nebula.carDealership.service;

import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.exceptions.InvalidDataException;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CarService {

    private CarRepository carRepository;


    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> addCar(List<Car> carList) {
        List<Car> newCars = new ArrayList<>();

        for (Car car : carList) {
            if ((car.getBrand() == null) || car.getBrand().isEmpty() ||
                    (car.getModel() == null) || car.getModel().isEmpty() ||
                    (car.getYear() == null) || (car.getYear().toString().length() != 4) ||
                    (car.getPrice() == null) || car.getPrice().equals(0) ||
                    (car.getMileage() == null) || car.getMileage().equals(0) ||
                    (car.getColour() == null) || car.getColour().isEmpty()) {

                throw new InvalidDataException(String.valueOf(Map.of("Description", "Incorrect car data provided")), HttpStatus.BAD_REQUEST);
            }

            if (carRepository.existsByBrandAndModelAndYearAndPriceAndMileageAndColour(
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getPrice(),
                    car.getMileage(),
                    car.getColour())) {
                throw new CarAlreadyExistsException("Description", "Car already exists");
            }

            newCars.add(car);
        }

        return carRepository.saveAll(newCars);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getBrand(String brand) {
        List<Car> brandList = carRepository.findByBrand(brand).stream()
                .sorted(Comparator.comparingInt(Car::getYear).reversed())
                .collect(Collectors.toList());

        return brandList;
    }

    public List<Car> getModel(String model) {
        List<Car> modelList = carRepository.findByModel(model).stream()
                .sorted(Comparator.comparingInt(Car::getPrice))
                .collect(Collectors.toList());

        return modelList;
    }

    public List<Car> getYear(int year) {
        List<Car> yearList = carRepository.findByYear(year).stream()
                .sorted(Comparator.comparing(car -> car.getBrand().toLowerCase()))
                .collect(Collectors.toList());

        return yearList;
    }

    public List<Car> getPrice(int price) {
        List<Car> priceList = carRepository.findByPrice(price).stream()
                .sorted(Comparator.comparing(Car::getYear).reversed())
                .collect(Collectors.toList());

        return priceList;
    }

    public List<Car> getMileage(int mileage) {
        List<Car> mileageList = carRepository.findByMileage(mileage).stream()
                .sorted(Comparator.comparing(car -> car.getBrand().toLowerCase()))
                .collect(Collectors.toList());

        return mileageList;
    }

    public List<Car> getColour(String colour) {
        List<Car> colourList = carRepository.findByColour(colour).stream()
                .sorted(Comparator.comparing(car -> car.getModel().toLowerCase()))
                .collect(Collectors.toList());

        return colourList;
    }

    public void deleteAllCars() {
            carRepository.deleteAll();
    }

}

