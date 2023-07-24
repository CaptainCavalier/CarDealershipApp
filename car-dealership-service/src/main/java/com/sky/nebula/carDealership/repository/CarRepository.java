package com.sky.nebula.carDealership.repository;

import com.sky.nebula.carDealership.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByBrandAndModelAndYearAndPriceAndMileageAndColour(
            String brand,
            String model,
            Integer year,
            Integer price,
            Integer mileage,
            String colour
    );
    List<Car> findByBrand(String brand);
    List<Car> findByModel(String model);
    List<Car> findByYear(int year);
    List<Car> findByPrice(int price);
    List<Car> findByMileage(int mileage);
    List<Car> findByColour(String colour);
}

