package com.sky.nebula.carDealership.service;

import com.sky.nebula.carDealership.exceptions.CarAlreadyExistsException;
import com.sky.nebula.carDealership.exceptions.InvalidDataException;
import com.sky.nebula.carDealership.exceptions.InvalidQueryParameterException;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import com.sky.nebula.carDealership.validators.RequestParameterValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CarService {

    RequestParameterValidation validator = new RequestParameterValidation();

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

        // Check brand does not consist of only digits
        if (brand.matches("\\d+")) {
            throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
        }

        // Calls method to validate request parameter variable
        if (validator.validateString(brand)) {

            //stream to create list, find by brand, and sort by newest to oldest
            List<Car> brandList = carRepository.findByBrand(brand).stream()
                    .sorted(Comparator.comparingInt(Car::getYear).reversed())
                    .collect(Collectors.toList());

            return brandList;
        }

        throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
    }

    public List<Car> getModel(String model) {
        // Models can be numbers exclusively, no matching required

        if (validator.validateString(model)) {
            List<Car> modelList = carRepository.findByModel(model).stream()
                    .sorted(Comparator.comparingInt(Car::getPrice))
                    .collect(Collectors.toList());

            return modelList;
        }

        throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
    }

    public List<Car> getYear(String year) {
        if (validator.validateString(year)) {

            // Using try catch block to catch letters where there should just be digits
            try {
                // Parsing variable to an int in order to run find by year
                List<Car> yearList = carRepository.findByYear(Integer.parseInt(year)).stream()
                        .sorted(Comparator.comparing(car -> car.getBrand().toLowerCase()))
                        .collect(Collectors.toList());

                return yearList;
            } catch (NumberFormatException e) {
                throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
            }
        }

        throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
    }

    public List<Car> getPrice(String price) {
        if (validator.validateString(price)) {
            try {
                List<Car> priceList = carRepository.findByPrice(Integer.parseInt(price)).stream()
                        .sorted(Comparator.comparing(Car::getYear).reversed())
                        .collect(Collectors.toList());

                return priceList;
            } catch (NumberFormatException e) {
                throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
            }
        }

        throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
    }

    public List<Car> getMileage(String mileage) {
        if (validator.validateString(mileage)) {
            try {
                List<Car> mileageList = carRepository.findByMileage(Integer.parseInt(mileage)).stream()
                        .sorted(Comparator.comparing(car -> car.getBrand().toLowerCase()))
                        .collect(Collectors.toList());

                return mileageList;
            } catch (NumberFormatException e) {
                throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
            }
        }

        throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
    }

    public List<Car> getColour(String colour) {

        // Check brand does not consist of only digits
        if (colour.matches("\\d+")) {
            throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
        }

        if (validator.validateString(colour)) {
            List<Car> colourList = carRepository.findByColour(colour).stream()
                    .sorted(Comparator.comparing(car -> car.getModel().toLowerCase()))
                    .collect(Collectors.toList());
            return colourList;
        }

        throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
    }

    public void deleteAllCars() {
            carRepository.deleteAll();
    }

}

