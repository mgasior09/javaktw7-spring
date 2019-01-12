package pl.sdacademy.spring.car_dealer.service;

import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

import java.util.List;

public interface CarDataService {
    List<Vehicle> loadCarsThatCanBeSold();
    void setVehicleRepository(VehicleRepository vehicleRepository);
}
