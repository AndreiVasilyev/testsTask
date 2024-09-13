package by.clevertec.mapper;

import by.clevertec.domain.Car;
import by.clevertec.entity.CarEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {

    List<Car> toDomains(List<CarEntity> cars);

    Car toDomain(CarEntity car);

    CarEntity toEntity(Car car);
}
