package pl.sdacademy.spring.car_dealer.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
