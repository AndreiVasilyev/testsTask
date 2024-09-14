package by.clevertec.service;

import by.clevertec.common.Body;
import by.clevertec.domain.Car;
import by.clevertec.entity.CarEntity;
import by.clevertec.exception.CarServiceException;
import by.clevertec.mapper.CarMapper;
import by.clevertec.repository.CarRepository;
import by.clevertec.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    CarMapper carMapper;

    @InjectMocks
    private CarService carService;

    @Test
    void shouldGetCars() {
        //given
        Optional<List<CarEntity>> carEntities = Optional.of(TestData.generateCarEntityList());
        Optional<List<Car>> cars = Optional.of(TestData.generateCarList());
        when(carRepository.getCars()).thenReturn(carEntities);
        when(carMapper.toDomains(carEntities.get())).thenReturn(cars.get());

        //when
        List<Car> actualCars = carService.getCars();

        //then
        assertFalse(actualCars.isEmpty());
    }

    @Test
    void shouldGetCarById() {
        //given
        UUID carId = UUID.randomUUID();
        Car expectedCar = TestData.generateCar(carId);
        CarEntity carEntity = TestData.generateCarEntity(carId);
        when(carRepository.getCarById(carId)).thenReturn(Optional.of(carEntity));
        when(carMapper.toDomain(carEntity)).thenReturn(expectedCar);

        //when
        Car actualCar = carService.getCarById(carId);

        //then
        assertEquals(expectedCar, actualCar);

    }

    @ParameterizedTest
    @EnumSource
    void shouldGetCarsByBody(Body body) {
        //given
        UUID carId = UUID.randomUUID();
        CarEntity expectedCarEntity = TestData.generateCarEntity(carId);
        expectedCarEntity.setBody(body);
        List<CarEntity> carEntities = List.of(expectedCarEntity);
        when(carRepository.getCarByBody(body)).thenReturn(Optional.of(carEntities));

        //when then
        assertDoesNotThrow(() -> carService.getCarsByBody(body));
        assertEquals(body, carEntities.getFirst().getBody());
    }


    @Test
    void shouldThrowExceptionWhenCarNotFound() {
        //given
        UUID carId = UUID.randomUUID();
        when(carRepository.getCarById(carId)).thenReturn(Optional.empty());

        //when then
        assertThrows(CarServiceException.class, () -> carService.getCarById(carId));
        verifyNoInteractions(carMapper);
    }

    @Test
    void shouldCreateCar() {
        //given
        UUID carId = UUID.randomUUID();
        Car newCar = TestData.generateCar(carId);
        CarEntity newCarEntity = TestData.generateCarEntity(carId);
        when(carRepository.createCar(newCarEntity)).thenReturn(Optional.of(newCarEntity));
        when(carMapper.toEntity(newCar)).thenReturn(newCarEntity);
        when(carMapper.toDomain(newCarEntity)).thenReturn(newCar);

        //when
        Car createdCar = carService.createCar(newCar);

        //then
        assertEquals(newCar, createdCar);
    }

    @Test
    void shouldDeleteCar() {
        //given
        UUID carId = UUID.randomUUID();

        //when then
        carService.deleteCar(carId);
    }

    @Test
    void updateCar() {
        //given
        UUID carId = UUID.randomUUID();
        Car newCar = TestData.generateCar(carId);
        CarEntity newCarEntity = TestData.generateCarEntity(carId);
        when(carRepository.updateCar(carId, newCarEntity)).thenReturn(Optional.of(newCarEntity));
        when(carMapper.toEntity(newCar)).thenReturn(newCarEntity);
        when(carMapper.toDomain(newCarEntity)).thenReturn(newCar);

        //when
        Car createdCar = carService.updateCar(carId, newCar);

        //then
        assertEquals(newCar, createdCar);
    }
}