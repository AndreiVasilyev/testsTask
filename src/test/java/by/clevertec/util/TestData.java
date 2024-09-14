package by.clevertec.util;

import by.clevertec.common.Body;
import by.clevertec.domain.Car;
import by.clevertec.entity.CarEntity;

import java.awt.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class TestData {

    private static DateSupplierTest dateSupplierTest = new DateSupplierTest();

    public static CarEntity generateCarEntity() {
        return new CarEntity(UUID.randomUUID(), Body.SEDAN, "VECTRA", dateSupplierTest.getCurrentDateTime(), Color.BLACK);
    }

    public static CarEntity generateCarEntity(UUID uuid) {
        return new CarEntity(uuid, Body.SEDAN, "VECTRA", dateSupplierTest.getCurrentDateTime(), Color.BLACK);
    }

    public static List<CarEntity> generateCarEntityList() {
        return List.of(generateCarEntity());
    }

    public static List<CarEntity> generateCarEntityList(int size) {
        return Stream.generate(TestData::generateCarEntity).limit(size)
                .toList();
    }

    public static Car generateCar() {
        return new Car(UUID.randomUUID(), Body.SEDAN, "VECTRA", dateSupplierTest.getCurrentDateTime());
    }

    public static Car generateCar(UUID uuid) {
        return new Car(uuid, Body.SEDAN, "VECTRA", dateSupplierTest.getCurrentDateTime());
    }

    public static List<Car> generateCarList() {
        return List.of(generateCar());
    }

    public static List<Car> generateSameCarList(List<CarEntity> carEntityList) {
        return carEntityList.stream()
                .map(carEntity -> generateCar(carEntity.getUuid()))
                .toList();
    }
}
