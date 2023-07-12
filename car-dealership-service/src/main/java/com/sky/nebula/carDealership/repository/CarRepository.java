package com.sky.nebula.carDealership.repository;

import com.sky.nebula.carDealership.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;


public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByBrandAndModelAndYearAndPriceAndMileageAndColour(
            String brand,
            String model,
            Integer year,
            Integer price,
            Integer mileage,
            String colour
    );
}

