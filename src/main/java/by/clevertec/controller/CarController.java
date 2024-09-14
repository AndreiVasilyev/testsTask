package by.clevertec.controller;

import by.clevertec.domain.Car;
import by.clevertec.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
