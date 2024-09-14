package by.clevertec.mapper;

import by.clevertec.domain.Car;
import by.clevertec.entity.CarEntity;
import by.clevertec.util.TestData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarMapperTest {

    private CarMapper carMapper = new CarMapperImpl();

    @Test
    void toDomains() {
        //given
        List<CarEntity> carEntityList = TestData.generateCarEntityList(3);
        List<Car> expectedCarList = TestData.generateSameCarList(carEntityList);
        //when
        List<Car> actualCarList = carMapper.toDomains(carEntityList);
        //then
        assertEquals(expectedCarList, actualCarList);
    }

    @Test
    void shouldReturnNullWhenMapToDomains() {
        //given, when, then
        assertNull(carMapper.toDomains(null));
    }

    @Test
    void shouldMapToDomain() {
        //given
        UUID uuid = UUID.randomUUID();
        CarEntity carEntity = TestData.generateCarEntity(uuid);
        Car expectedCar = TestData.generateCar(uuid);
        //when
        Car actualCar = carMapper.toDomain(carEntity);
        //then
        assertEquals(expectedCar, actualCar);
    }

    @Test
    void shouldReturnNullWhenMapToDomain() {
        //given, when, then
        assertNull(carMapper.toDomain(null));
    }

    @Test
    void shouldMapToEntity() {
        //given
        UUID uuid = UUID.randomUUID();
        CarEntity expectedCarEntity = TestData.generateCarEntity(uuid);
        expectedCarEntity.setColor(null);
        Car car = TestData.generateCar(uuid);
        //when
        CarEntity actualCarEntity = carMapper.toEntity(car);
        //then
        assertEquals(expectedCarEntity, actualCarEntity);
    }

    @Test
    void shouldReturnNullWhenMapToEntity() {
        //given, when, then
        assertNull(carMapper.toEntity(null));
    }
}