package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.spring.car_dealer.model.PurchaseFormData;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.service.CarDataService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicles")
public class CarDataController {

    private final CarDataService carDataService;

    CarDataController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }


    @GetMapping
    public String printAvailableCars(Model model) {
        List<Vehicle> vehicles = carDataService.loadCarsThatCanBeSold();
        vehicles.sort(Comparator.comparing(Vehicle::getId));
        model.addAttribute("vehicleList", vehicles);
        return "vehicles";
    }

    @RequestMapping("/{id}")
    public String getCar(@PathVariable("id") Long vehicleId, Model model) {
        Optional<Vehicle> foundVehicle = carDataService.getById(vehicleId);
        foundVehicle.ifPresent(vehicle -> model.addAttribute("vehicle", vehicle));
        return "vehicleDetails";
    }

    @GetMapping("/add")
    public String initCarAddForm(Model model) {
        model.addAttribute("addedVehicle", new Vehicle());
        return "addVehicle";
    }

    @PostMapping
    public String saveVehicle(@ModelAttribute("addedVehicle") Vehicle vehicle) {
        carDataService.addVehicle(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/{vehId}/sell")
    public String initCarSellForm(@PathVariable("vehId")Long vehicleId, Model model) {
        PurchaseFormData pfm = new PurchaseFormData();
        pfm.setVehicleId(vehicleId);
        model.addAttribute("formData", pfm);
        return "sellVehicle";
    }
}
