package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.qualifier.HardDriveStorage;

import java.util.List;

@Repository
@HardDriveStorage
public class HardDriveCustomerRepository extends AbstractHardDriveRepository<Customer> implements CustomerRepository {
    private final String repositoryLocation;

   HardDriveCustomerRepository(@Value("${repository.customer.hardDrive.fileLocation}") String repositoryLocation) {
        this.repositoryLocation = repositoryLocation;
    }

    public Customer byId(Long id) {
        return loadAllElements().stream().filter(customer -> customer.getId().equals(id)).findAny().orElse(null);
    }

    public Customer add(Customer customer) {
        List<Customer> customers = loadAllElements();
        Long newCustomerId = getNextId(customers);
        customer.setId(newCustomerId);
        customers.add(customer);
        saveAllElements(customers);
        return customer;
    }

    @Override
    protected String getRepositoryLocation() {
        return repositoryLocation;
    }

    public void initialize() {
        System.out.println("Initializing repository");
    }

    public void cleanUp() {
        System.out.println("Cleaning up repository");
    }
}
