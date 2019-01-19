package pl.sdacademy.spring.car_dealer.service;

import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface CarDataService {
    List<Vehicle> loadCarsThatCanBeSold();

    Vehicle addVehicle(Vehicle vehicle);

    Optional<Vehicle> getById(Long vehicleId);
}
