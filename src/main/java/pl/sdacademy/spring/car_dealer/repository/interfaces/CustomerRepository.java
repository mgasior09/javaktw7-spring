package pl.sdacademy.spring.car_dealer.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import pl.sdacademy.spring.car_dealer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
