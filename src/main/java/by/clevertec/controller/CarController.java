package by.clevertec.controller;

import by.clevertec.domain.Car;
import by.clevertec.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/api/cars")
    public ResponseEntity<List<Car>> findAll() {
        List<Car> cars = carService.getCars();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cars);
    }

    @GetMapping("/api/car/{id}")
    public ResponseEntity<Car> findById(@PathVariable(name = "id") String id) {
        Car car = carService.getCarById(UUID.fromString(id));
        return car != null ? ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(car)
                : ResponseEntity.notFound().build();
    }


    @PostMapping("/api/car/new")
    public ResponseEntity<Car> create(@RequestBody Car car) {
        Car newCar = carService.createCar(car);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newCar);
    }

    @PutMapping("/api/car/update/{id}")
    public ResponseEntity<Car> update(@PathVariable(name = "id") String id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(UUID.fromString(id), car);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedCar);
    }

    @DeleteMapping("/api/car/delete/{id}")
    public ResponseEntity<Car> delete(@PathVariable(name = "id") String id) {
        carService.deleteCar(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

}
