package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.interfaces.CustomerRepository;
import pl.sdacademy.spring.car_dealer.repository.interfaces.PurchaseRepository;
import pl.sdacademy.spring.car_dealer.repository.interfaces.VehicleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultSellingService implements SellingService {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final PurchaseRepository purchaseRepository;

    DefaultSellingService(VehicleRepository vehicleRepository, CustomerRepository customerRepository, PurchaseRepository purchaseRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.purchaseRepository = purchaseRepository;
    }


    @Transactional
    public Purchase sell(Long vehicleId, Customer customer, Long price) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findAvailableById(vehicleId);
        if (!optionalVehicle.isPresent()) {
            return null;
        } else {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setSold(true);
            vehicleRepository.save(vehicle);
            Optional<Customer> foundCustomer = customerRepository.findCustomerByDocumentNo(customer.getDocumentNo());
            Purchase purchase = new Purchase();
            purchase.setVehicle(vehicle);
            purchase.setCustomer(foundCustomer.orElseGet(() -> customerRepository.save(customer)));
            purchase.setDate(new Date());
            purchase.setPrice(price);
            return purchaseRepository.save(purchase);
        }
    }

    public List<Purchase> getPurchases(String documentNo) {
        return purchaseRepository.findByCustomerDocumentNoEquals(documentNo);
    }

    public List<Purchase> getPurchases(Long minPrice, Long maxPrice) {
        return purchaseRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public Optional<Purchase> getById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }
}
