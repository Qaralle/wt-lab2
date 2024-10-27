package ru.itmo.service;

import ru.itmo.model.Car;
import ru.itmo.model.dao.CarDao;
import ru.itmo.model.dao.CarRepository;
import ru.itmo.service.status.OperationStatus;

import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "CarService",
        endpointInterface = "ru.itmo.service.CarService",
        targetNamespace = "http://service.itmo.ru/")
public class CarServiceImpl implements CarService {
    private final CarDao carRepository;

    public CarServiceImpl() {
        this.carRepository = new CarRepository();
    }

    @Override
    public List<Car> searchCars(Long id, String name, Integer price, Integer count, Integer power)  {
        return carRepository.findCars(id, name, price, count, power);
    }

    @Override
    public OperationStatus updateCar(Long id, String name, Integer price, Integer count, Integer power) {
        Car car = new Car();

        car.setId(id);
        car.setName(name);
        car.setPrice(price);
        car.setCount(count);
        car.setPower(power);

        return carRepository.updateCar(car) == 0 ? OperationStatus.NOT_FOUND : OperationStatus.SUCCESS;
    }

    @Override
    public OperationStatus deleteCar(Long id) {
        return carRepository.deleteCarById(id) == 0 ? OperationStatus.NOT_FOUND : OperationStatus.SUCCESS;
    }

    @Override
    public Long createCar(String name, Integer price, Integer count, Integer power) {
        Car car = new Car();

        car.setName(name);
        car.setPrice(price);
        car.setCount(count);
        car.setPower(power);

        return carRepository.saveCar(car);
    }
}
