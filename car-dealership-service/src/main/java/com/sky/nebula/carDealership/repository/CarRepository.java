package com.sky.nebula.carDealership.repository;

import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.service.CarService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

}
