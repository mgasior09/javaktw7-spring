package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.service.CarDataService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
@RequestMapping("/vehicles")
public class CarDataController {

    private final CarDataService carDataService;

    CarDataController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }

    @RequestMapping
    public String printAvailableCars(Model model) {
        List<Vehicle> vehicles = carDataService.loadCarsThatCanBeSold();
        vehicles.sort(Comparator.comparing(Vehicle::getId));
       model.addAttribute("vehicleList", vehicles);
        return "vehicles";
    }

    public void addVehicle() {
        System.out.println("Adding new vehicle");
        Vehicle vehicle = new Vehicle();
        System.out.println("Enter manufacturer");
        vehicle.setManufacturer(readUserInput());
        System.out.println("Enter model");
        vehicle.setModel(readUserInput());
        System.out.println("Set fuel");
        vehicle.setFuel(readUserInput());
        System.out.println("Set production year");
        vehicle.setProductionYear(readNumberUserInput());
        System.out.println("Set mileage");
        vehicle.setMileage(readNumberUserInput());
        System.out.println("Set price");
        vehicle.setPrice(readNumberUserInput());
        carDataService.addVehicle(vehicle);
    }

    public String readUserInput() {
        return new Scanner(System.in).nextLine();
    }

    public Long readNumberUserInput() {
        try {
            return Long.parseLong(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    @RequestMapping("/{id}")
    public String getCar(@PathVariable("id") Long vehicleId, Model model) {
        Optional<Vehicle> foundVehicle = carDataService.getById(vehicleId);
        foundVehicle.ifPresent(vehicle -> model.addAttribute("vehicle", vehicle));
        return "vehicleDetails";
    }
}
