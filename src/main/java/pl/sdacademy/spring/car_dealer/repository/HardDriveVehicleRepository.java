package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.qualifier.HardDriveStorage;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@HardDriveStorage
public class HardDriveVehicleRepository extends AbstractHardDriveRepository<Vehicle> implements VehicleRepository {

    private final String repositoryLocation;

    HardDriveVehicleRepository(@Value("${repository.vehicle.hardDrive.fileLocation}") String repositoryLocation) {
        this.repositoryLocation = repositoryLocation;
    }

    @Override
    public Vehicle byId(Long id) {
        return loadAllElements().stream().filter(vehicle -> vehicle.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public List<Vehicle> getAll() {
        return loadAllElements();
    }

    @Override
    public List<Vehicle> getAvailable() {
        return loadAllElements().stream().filter(vehicle -> !vehicle.isSold()).collect(Collectors.toList());
    }

    @Override
    public Vehicle update(Vehicle newVehicle) {
        List<Vehicle> allVehicles = loadAllElements();
        List<Vehicle> filteredOutVehicles = allVehicles.stream().filter(oldVehicle -> !oldVehicle.getId().equals(newVehicle.getId())).collect(Collectors.toList());
        filteredOutVehicles.add(newVehicle);
        saveAllElements(filteredOutVehicles);
        return newVehicle;
    }

    public void initialize() {
        System.out.println("Initializing repository");
    }

    public void cleanUp() {
        System.out.println("Cleaning up repository");
    }

    @Override
    protected String getRepositoryLocation() {
        return repositoryLocation;
    }
}
