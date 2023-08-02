package com.sky.nebula.carDealership.validators;

import com.sky.nebula.carDealership.model.Car;

public class CarValidator {


    public static boolean isCarDataValid(Car car) {
        return car.getBrand() != null && !car.getBrand().isEmpty() &&
                car.getModel() != null && !car.getModel().isEmpty() &&
                car.getYear() != null && car.getYear().toString().length() == 4 &&
                car.getPrice() != null && car.getPrice() > 0 &&
                car.getMileage() != null && car.getMileage() > 0 &&
                car.getColour() != null && !car.getColour().isEmpty();
    }

}
