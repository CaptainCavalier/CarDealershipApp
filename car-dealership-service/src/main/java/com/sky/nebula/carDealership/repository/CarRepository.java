package com.sky.nebula.carDealership.repository;

import com.sky.nebula.carDealership.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CarRepository extends JpaRepository<Car, Long> {

}
