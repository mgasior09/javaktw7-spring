package pl.sdacademy.spring.car_dealer.service;

import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.repository.CustomerRepository;
import pl.sdacademy.spring.car_dealer.repository.PurchaseRepository;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

public interface SellingService {
    Purchase sell(Long vehicleId, Customer customer, Long price);
    void setVehicleRepository(VehicleRepository vehicleRepository);
    void setCustomerRepository(CustomerRepository customerRepository);
    void setPurchaseRepository(PurchaseRepository purchaseRepository);
}
