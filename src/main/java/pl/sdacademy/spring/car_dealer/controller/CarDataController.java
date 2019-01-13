package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.service.CarDataService;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Controller
public class CarDataController {

    private final CarDataService carDataService;

    CarDataController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }

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

    public void addNewCar() {
        Vehicle vehicle = new Vehicle();
        System.out.println("Enter manufacturer");
        vehicle.setManufacturer(userInput());
        System.out.println("Enter model");
        vehicle.setModel(userInput());
        System.out.println("Enter fuel");
        vehicle.setFuel(userInput());
        System.out.println("Set production year");
        vehicle.setProductionYear(Long.parseLong(userInput()));
        System.out.println("Set mileage");
        vehicle.setMileage(Long.parseLong(userInput()));
        System.out.println("Set price");
        vehicle.setPrice(Long.parseLong(userInput()));
        vehicle.setSold(false);
        carDataService.addVehicle(vehicle);
    }

    public String userInput() {
        return new Scanner(System.in).nextLine();
    }
}
