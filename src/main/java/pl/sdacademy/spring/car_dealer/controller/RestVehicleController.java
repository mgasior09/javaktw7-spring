package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.interfaces.VehicleRepository;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestVehicleController {
    private final VehicleRepository vehicleRepository;

    public RestVehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public Collection<Vehicle> getAllVehicles() {
        return (Collection<Vehicle>) vehicleRepository.findAll();
    }

    @RequestMapping("/{id}")
    public Vehicle getVehicle(@PathVariable("id") Long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElse(null);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vehicle addVehicle(@Valid @RequestBody Vehicle vehicle) {
      return   vehicleRepository.save(vehicle);
    }


}
