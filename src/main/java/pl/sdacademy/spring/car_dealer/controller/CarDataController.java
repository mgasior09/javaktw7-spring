package pl.sdacademy.spring.car_dealer.controller;

import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.service.CarDataService;

import java.util.Comparator;
import java.util.List;

public class CarDataController {

    private CarDataService carDataService;

    public void printAvailableCars() {
        List<Vehicle> vehicles = carDataService.loadCarsThatCanBeSold();
        vehicles.sort(Comparator.comparing(Vehicle::getId));
        vehicles.forEach(vehicle -> {
            System.out.println(String.format("%d: %s %s from %d with %s powered engine and total mileage of %d for only %d!",
                    vehicle.getId(),
                    vehicle.getManufacturer(),
                    vehicle.getModel(),
                    vehicle.getProductionYear(),
                    vehicle.getFuel(),
                    vehicle.getMileage(),
                    vehicle.getPrice()));
        });
    }

    public void setCarDataService(CarDataService carDataService) {
        this.carDataService = carDataService;
    }
}
