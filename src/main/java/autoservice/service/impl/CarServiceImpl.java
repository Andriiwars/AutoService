package autoservice.service.impl;

import autoservice.dao.CarDao;
import autoservice.model.Car;
import autoservice.service.CarService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public Car getCarById(Long id) {
        return carDao.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get car by id " + id));
    }

    @Override
    public Car createCar(Car car) {
        return carDao.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carDao.findAll();
    }

    @Override
    public void deleteCarById(Long id) {
        carDao.deleteById(id);
    }
}
