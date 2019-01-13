package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.finders.VehicleFinder;
import pl.sdacademy.spring.car_dealer.repository.interfaces.VehicleRepository;

import java.util.List;

@Service
public class DefaultCarDataService implements CarDataService {

    private final VehicleFinder vehicleFinder;
    private  final VehicleRepository vehicleRepository;

    DefaultCarDataService(VehicleFinder vehicleFinder, VehicleRepository vehicleRepository) {
        this.vehicleFinder = vehicleFinder;
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> loadCarsThatCanBeSold() {
        return vehicleFinder.getAvailable();
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
       vehicleRepository.save(vehicle);
        return vehicle;
    }
}
