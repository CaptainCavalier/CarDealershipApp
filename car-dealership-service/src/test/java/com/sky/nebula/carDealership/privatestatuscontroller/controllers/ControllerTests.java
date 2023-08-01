package com.sky.nebula.carDealership.privatestatuscontroller.controllers;

import com.sky.nebula.carDealership.controllers.CarController;
import com.sky.nebula.carDealership.model.Car;
import com.sky.nebula.carDealership.repository.CarRepository;
import com.sky.nebula.carDealership.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControllerTests {

    CarRepository carRepository = mock(CarRepository.class);
    CarService carService = new CarService(carRepository);
    CarController carController = new CarController(carService);


    // Need to test car is actually added in the addCar method
    @Test
    void addCarEndPointReturns201AndResponse() {

        ResponseEntity<Map<String, String>> response = carController.addCar(
                List.of(new Car(
                "BMW",
                "X5",
                2022,
                80000,
                10000,
                "space grey")));

        String key = "Description";
        String value = "Database Updated";

        Assertions.assertTrue(response.getBody().containsKey(key));
        Assertions.assertTrue(response.getBody().containsValue(value));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getAllCarsEndpointReturns200AndResponse() {
        // Arrange
        Car car1 = new Car("BMW", "X5", 2022, 80000, 10000, "space grey");
        Car car2 = new Car("BMW", "X6", 2023, 100000, 1000, "sky blue");
        List<Car> carList =  List.of(car1, car2);

        when(carService.getAllCars()).thenReturn(carList);

        // Act
        ResponseEntity<List<Car>> response = carController.getAllCars();

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(carList, response.getBody());

        System.out.println(response);
    }

    @Test
    public void getCarByBrandSortedByYearReturns200AndResponse() {
        String targetBrand = "BMW";

        // Arrange
        Car car1 = new Car(targetBrand, "X5", 2007, 80000, 10000, "space grey");
        Car car2 = new Car(targetBrand, "X3", 2021, 75000, 12000, "white");
        Car car3 = new Car("Nissan", "Micra", 2006, 800, 100000, "black");

        List<Car> allCars = List.of(car1, car2, car3);

        // Mocks list of cars with target brand
        Mockito.when(carService.getBrand(targetBrand))
                .thenReturn(allCars.stream()
                        .filter(car -> car.getBrand().equals(targetBrand))
                        .sorted(Comparator.comparingInt(Car::getYear).reversed())
                        .collect(Collectors.toList()));

        // Act
        ResponseEntity<List<Car>> response = carController.getBrand(targetBrand);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allCars.stream()
                .filter(car -> car.getBrand().equals(targetBrand))
                .sorted(Comparator.comparingInt(Car::getYear).reversed())
                .collect(Collectors.toList()), response.getBody());

        System.out.println(response);
    }

    @Test
    public void getCarByModelSortedByPriceReturns200AndResponse() {
        String targetModel = "Micra";

        // Arrange
        Car car1 = new Car("BMW", "X5", 2022, 80000, 10000, "space grey");
        Car car2 = new Car("Nissan", targetModel, 2006, 8000, 100000, "white");
        Car car3 = new Car("Nissan", targetModel, 2006, 80, 100000, "black");

        List<Car> allCars = List.of(car1, car2, car3);

        Mockito.when(carService.getModel(targetModel))
                .thenReturn(allCars.stream()
                        .filter(car -> car.getModel().equals(targetModel))
                        .sorted(Comparator.comparingInt(Car::getPrice))
                        .collect(Collectors.toList()));

        // Act
        ResponseEntity<List<Car>> response = carController.getModel(targetModel);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allCars.stream()
                .filter(car -> car.getModel().equals(targetModel))
                .sorted(Comparator.comparingInt(Car::getPrice))
                .collect(Collectors.toList()), response.getBody());

        System.out.println(response);
    }

    @Test
    public void getCarByYearSortedByBrandAlphabeticallyReturns200AndResponse() {
        int targetYear = 2006;

        // Arrange
        Car car1 = new Car("BMW", "X5", 2022, 80000, 10000, "space grey");
        Car car2 = new Car("Bugatti", "Veron", targetYear, 1000000, 10, "black");
        Car car3 = new Car("Nissan", "Micra", targetYear, 800, 100000, "black");
        Car car4 = new Car("BMW", "X6", targetYear, 100000, 1000, "black");

        List<Car> allCars = List.of(car1, car2, car3, car4);

        // Mocks list of cars with target brand
        Mockito.when(carService.getYear(String.valueOf(targetYear)))
                .thenReturn(allCars.stream()
                        .filter(car -> car.getYear().equals(targetYear))
                        .sorted(Comparator.comparing(car -> car.getBrand().toLowerCase()))
                        .collect(Collectors.toList()));

        // Act
        ResponseEntity<List<Car>> response = carController.getYear(String.valueOf(targetYear));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allCars.stream()
                .filter(car -> car.getYear().equals(targetYear))
                .sorted(Comparator.comparing(car -> car.getBrand().toLowerCase()))
                .collect(Collectors.toList()), response.getBody());

        System.out.println(response);
    }

    @Test
    public void getCarByPriceSortedByYearReturns200AndResponse() {
        int targetPrice = 80000;

        // Arrange
        Car car1 = new Car("BMW", "X5", 2022, targetPrice, 10000, "space grey");
        Car car2 = new Car("BMW", "X3", 2023, targetPrice, 12000, "white");
        Car car3 = new Car("Nissan", "Micra", 2006, 800, 100000, "black");
        Car car4 = new Car("Nissan", "Micra", 2006, targetPrice, 100000, "black");

        List<Car> allCars = List.of(car1, car2, car3, car4);

        // Mocks list of cars with target brand
        Mockito.when(carService.getPrice(String.valueOf(targetPrice)))
                .thenReturn(allCars.stream()
                        .filter(car -> car.getPrice().equals(targetPrice))
                        .sorted(Comparator.comparingInt(Car::getYear).reversed())
                        .collect(Collectors.toList()));

        // Act
        ResponseEntity<List<Car>> response = carController.getPrice(String.valueOf(targetPrice));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allCars.stream()
                .filter(car -> car.getPrice().equals(targetPrice))
                .sorted(Comparator.comparingInt(Car::getYear).reversed())
                .collect(Collectors.toList()), response.getBody());

        System.out.println(response);
    }

    @Test
    public void getCarByMileageSortedByBrandAlphabeticallyReturns200AndResponse() {
        int targetMileage = 10000;

        // Arrange
        Car car1 = new Car("BMW", "X5", 2022, 80000, targetMileage, "space grey");
        Car car2 = new Car("Bentley", "X3", 2023, 100000, targetMileage, "white");
        Car car3 = new Car("Nissan", "Micra", 2006, 800, 100000, "black");
        Car car4 = new Car("Audi", "A5", 2006, 800, targetMileage, "black");

        List<Car> allCars = List.of(car1, car2, car3, car4);

        // Mocks list of cars with target brand
        Mockito.when(carService.getMileage(String.valueOf(targetMileage)))
                .thenReturn(allCars.stream()
                        .filter(car -> car.getMileage().equals(targetMileage))
                        .sorted(Comparator.comparing(car -> car.getBrand().toLowerCase()))
                        .collect(Collectors.toList()));

        // Act
        ResponseEntity<List<Car>> response = carController.getMileage(String.valueOf(targetMileage));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allCars.stream()
                .filter(car -> car.getMileage().equals(targetMileage))
                .sorted(Comparator.comparing(car -> car.getBrand().toLowerCase()))
                .collect(Collectors.toList()), response.getBody());

        System.out.println(response);
    }

    @Test
    public void getCarByColourSortedByModelAlphabeticallyReturns200AndResponse() {
        String  targetColour = "blue";

        // Arrange
        Car car1 = new Car("BMW", "X5", 2022, 80000, 1000, targetColour);
        Car car2 = new Car("BMW", "X3", 2023, 100000, 100, targetColour);
        Car car3 = new Car("Nissan", "Micra", 2006, 800, 100000, "black");
        Car car4 = new Car("Nissan", "Micra", 2008, 1000, 100000, targetColour);

        List<Car> allCars = List.of(car1, car2, car3, car4);

        // Mocks list of cars with target brand
        Mockito.when(carService.getColour(targetColour))
                .thenReturn(allCars.stream()
                        .filter(car -> car.getColour().equals(targetColour))
                        .sorted(Comparator.comparing(car -> car.getModel().toLowerCase()))
                        .collect(Collectors.toList()));

        // Act
        ResponseEntity<List<Car>> response = carController.getColour(targetColour);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allCars.stream()
                .filter(car -> car.getColour().equals(targetColour))
                .sorted(Comparator.comparing(car -> car.getModel().toLowerCase()))
                .collect(Collectors.toList()), response.getBody());

        System.out.println(response);
    }

}
