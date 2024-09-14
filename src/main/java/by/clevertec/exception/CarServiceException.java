package by.clevertec.exception;

import java.util.UUID;

public class CarServiceException extends RuntimeException {
    private CarServiceException(String message) {
        super(message);
    }

    public static CarServiceException byGetAllCars() {
        return new CarServiceException("Cars not found");
    }

    public static CarServiceException byId(UUID uuid) {
        return new CarServiceException(String.format("Car not found by id %s", uuid));
    }

    public static CarServiceException byBody(String body) {
        return new CarServiceException(String.format("Car not found by body type %s", body));
    }

    public static CarServiceException byCreateNewCar() {
        return new CarServiceException("Exception while creating new car");
    }

    public static CarServiceException byDeleteCar() {
        return new CarServiceException("Exception while delete car");
    }
}
