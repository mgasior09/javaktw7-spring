package pl.sdacademy.spring.car_dealer.repository.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.spring.car_dealer.model.Purchase;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {


}
