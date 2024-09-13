package by.clevertec.service;

import by.clevertec.domain.Car;
import by.clevertec.entity.CarEntity;
import by.clevertec.mapper.CarMapper;
import by.clevertec.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarService {

    private final CarRepository carRepository = new CarRepository();
    private CarMapper carMapper;

    public List<Car> getCars() {
        return carMapper.toDomains(carRepository.getCars());
    }

    public Car getCarById(UUID carId) {
        if (carRepository.getCarById(carId).isPresent()) {
            carMapper.toDomain(carRepository.getCarById(carId).get());
        }
        return null;
    }

    public Car createCar(Car car) {
        CarEntity domain = carMapper.toEntity(car);
        Optional<CarEntity> createdCar = carRepository.createCar(domain);
        return createdCar.map(carEntity -> carMapper.toDomain(carEntity)).orElse(null);
    }

    public void deleteCar(Car car) {
        CarEntity domain = carMapper.toEntity(car);
        carRepository.deleteCar(domain);
    }

    public Car updateCar(UUID carId, Car newCar) {
        CarEntity domain = carMapper.toEntity(newCar);
        Optional<CarEntity> updatedCar = carRepository.updateCar(carId, domain);
        return updatedCar.map(carEntity -> carMapper.toDomain(carEntity)).orElse(null);
    }

}
