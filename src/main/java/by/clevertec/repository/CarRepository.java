package by.clevertec.repository;

import by.clevertec.common.Body;
import by.clevertec.entity.CarEntity;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CarRepository {

    private static final List<CarEntity> dbCars = new ArrayList<>(List.of(
            new CarEntity(UUID.randomUUID(), Body.SEDAN, "PASSAT", LocalDateTime.now(), Color.CYAN),
            new CarEntity(UUID.randomUUID(), Body.SEDAN, "PASSAT", LocalDateTime.now(), Color.CYAN),
            new CarEntity(UUID.randomUUID(), Body.SUV, "RAV4", LocalDateTime.now(), Color.GREEN),
            new CarEntity(UUID.randomUUID(), Body.SEDAN, "VECTRA", LocalDateTime.now(), Color.BLACK),
            new CarEntity(UUID.randomUUID(), Body.CUV, "PAJERO", LocalDateTime.now(), Color.WHITE)));

    public Optional<List<CarEntity>> getCars() {
        return Optional.of(dbCars);
    }

    public Optional<CarEntity> getCarById(UUID carId) {
        return dbCars.stream()
                .filter(carEntity -> carEntity.getUuid().equals(carId))
                .findFirst();
    }

    public Optional<List<CarEntity>> getCarByBody(Body body) {
        return Optional.of(dbCars.stream()
                .filter(carEntity -> carEntity.getBody().equals(body))
                .toList());
    }

    public Optional<CarEntity> createCar(CarEntity car) {
        dbCars.add(car);
        return dbCars.stream()
                .filter(carEntity -> carEntity.getUuid().equals(car.getUuid()))
                .findFirst();
    }

    public void deleteCar(UUID uuid) {
        Optional<CarEntity> carEntity = dbCars.stream()
                .filter(car -> car.getUuid().equals(uuid))
                .findFirst();
        carEntity.ifPresent(dbCars::remove);
    }

    public Optional<CarEntity> updateCar(UUID carId, CarEntity newCar) {
        newCar.setUuid(carId);
        Optional<CarEntity> updatedCar = dbCars.stream()
                .filter(carEntity -> carEntity.getUuid().equals(carId))
                .findFirst();
        updatedCar.ifPresent(carEntity -> dbCars.set(dbCars.indexOf(carEntity), newCar));
        return Optional.of(newCar);
    }
}
