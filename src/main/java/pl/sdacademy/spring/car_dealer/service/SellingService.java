package pl.sdacademy.spring.car_dealer.service;

import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;

import java.util.List;
import java.util.Optional;

public interface SellingService {
    Purchase sell(Long vehicleId, Customer customer, Long price);

    List<Purchase> getPurchases(String documentNo);

    List<Purchase> getPurchases (Long minPrice, Long maxPrice);

    Optional<Purchase> getById(Long purchaseId);
}
