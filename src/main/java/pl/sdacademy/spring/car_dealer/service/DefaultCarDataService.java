package pl.sdacademy.spring.car_dealer.service;

import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

import java.util.List;

public class DefaultCarDataService implements CarDataService {
    private VehicleRepository vehicleRepository;

    public List<Vehicle> loadCarsThatCanBeSold() {
        return vehicleRepository.getAvailable();
    }

    public DefaultCarDataService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


}
