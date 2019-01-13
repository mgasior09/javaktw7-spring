package pl.sdacademy.spring.car_dealer.repository.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.spring.car_dealer.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query("FROM Customer c where  c.documentNo = :docNo")
    Optional<Customer> findCustomerByDocumentNo(@Param("docNo") String documentNo);
}
