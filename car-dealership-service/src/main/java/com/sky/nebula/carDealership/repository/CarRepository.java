package com.sky.nebula.carDealership.repository;

import com.sky.nebula.carDealership.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CarRepository extends JpaRepository<Car, Long> {



    Car findByBrand(String brand);
    Car findByModel(String model);
    Car findByYear(Integer year);
    Car findByPrice(Integer price);
    Car findByMileage(Integer mileage);
    Car findByColour(String colour);

//    Car findByAll(String brand, String model, Integer year, Integer price, Integer mileage, String colour);
}

