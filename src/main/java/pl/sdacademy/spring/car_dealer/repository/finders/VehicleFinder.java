package pl.sdacademy.spring.car_dealer.repository.finders;

import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.util.List;

public interface VehicleFinder {
    List<Vehicle> getAll();

    List<Vehicle> getAvailable();

    Long totalCount();

    Long totalCountForAvailable();

}
