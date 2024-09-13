package by.clevertec.repository;

import by.clevertec.common.Body;
import by.clevertec.entity.CarEntity;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarRepository {

    private static final List<CarEntity> dbCars = new ArrayList<>(List.of(new CarEntity(UUID.randomUUID(), Body.SEDAN, "PASSAT", LocalDateTime.now(), Color.CYAN),
            new CarEntity(UUID.randomUUID(), Body.SEDAN, "PASSAT", LocalDateTime.now(), Color.CYAN),
            new CarEntity(UUID.randomUUID(), Body.SUV, "RAV4", LocalDateTime.now(), Color.GREEN),
            new CarEntity(UUID.randomUUID(), Body.SEDAN, "VECTRA", LocalDateTime.now(), Color.BLACK),
            new CarEntity(UUID.randomUUID(), Body.CUV, "PAJERO", LocalDateTime.now(), Color.WHITE)));

    public List<CarEntity> getCars() {
        return dbCars;
    }

    public Optional<CarEntity> getCarById(UUID carId) {
        return dbCars.stream()
                .filter(carEntity -> carEntity.getUuid().equals(carId))
                .findFirst();
    }

    public Optional<CarEntity> createCar(CarEntity car) {
        dbCars.add(car);
        return dbCars.stream()
                .filter(carEntity -> carEntity.getUuid().equals(car.getUuid()))
                .findFirst();
    }

    public void deleteCar(CarEntity car) {
        dbCars.remove(car);
    }

    public Optional<CarEntity> updateCar(UUID carId, CarEntity newCar) {
        newCar.setUuid(carId);
        Optional<CarEntity> updatedCar = dbCars.stream()
                .filter(carEntity -> carEntity.getUuid().equals(carId))
                .findFirst();
        updatedCar.ifPresent(carEntity -> dbCars.set(dbCars.indexOf(carEntity), newCar));
        return updatedCar;
    }
}
