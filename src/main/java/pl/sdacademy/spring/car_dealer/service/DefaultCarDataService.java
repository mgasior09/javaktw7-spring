package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.VehicleFinder;

import java.util.List;

@Service
public class DefaultCarDataService implements CarDataService {

    private final VehicleFinder vehicleFinder;

    DefaultCarDataService(VehicleFinder vehicleFinder) {
        this.vehicleFinder = vehicleFinder;
    }

    public List<Vehicle> loadCarsThatCanBeSold() {
        return vehicleFinder.getAvailable();
    }


}
