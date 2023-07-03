package com.sky.nebula.carDealership.repository;

import com.sky.nebula.carDealership.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;


public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByBrand(String brand);
    Car findByModel(String model);
    Car findByYear(Integer year);
    Car findByPrice(Integer price);
    Car findByMileage(Integer mileage);
    Car findByColour(String colour);


}

