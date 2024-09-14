package by.clevertec.service;

import by.clevertec.common.Body;
import by.clevertec.domain.Car;
import by.clevertec.entity.CarEntity;
import by.clevertec.exception.CarServiceException;
import by.clevertec.mapper.CarMapper;
import by.clevertec.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<Car> getCars() {
        Optional<List<CarEntity>> carsList = carRepository.getCars();
        return carsList.map(carMapper::toDomains).orElseThrow(CarServiceException::byGetAllCars);
    }

    public Car getCarById(UUID carId) {
        Optional<CarEntity> carEntity = carRepository.getCarById(carId);
        return carEntity.map(carMapper::toDomain).orElseThrow(() -> CarServiceException.byId(carId));
    }

    public List<Car> getCarsByBody(Body body) {
        Optional<List<CarEntity>> carEntities = carRepository.getCarByBody(body);
        return carEntities.map(entities -> entities
                .stream()
                .map(carMapper::toDomain)
                .toList()).orElseThrow(() -> CarServiceException.byBody(body.name()));
    }

    public Car createCar(Car car) {
        CarEntity domain = carMapper.toEntity(car);
        Optional<CarEntity> createdCar = carRepository.createCar(domain);
        return createdCar.map(carMapper::toDomain).orElseThrow(CarServiceException::byCreateNewCar);
    }

    public void deleteCar(UUID carId) {
        carRepository.deleteCar(carId);
    }

    public Car updateCar(UUID carId, Car newCar) {
        CarEntity domain = carMapper.toEntity(newCar);
        Optional<CarEntity> updatedCar = carRepository.updateCar(carId, domain);
        return updatedCar.map(carMapper::toDomain).orElseThrow(CarServiceException::byDeleteCar);
    }

}
