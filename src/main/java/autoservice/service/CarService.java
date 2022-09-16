package autoservice.service;

import autoservice.model.Car;
import java.util.List;

public interface CarService {
    Car getCarById(Long id);

    Car createCar(Car car);

    List<Car> getAllCars();

    void deleteCarById(Long id);
}
