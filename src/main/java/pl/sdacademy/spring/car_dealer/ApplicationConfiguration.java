package pl.sdacademy.spring.car_dealer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sdacademy.spring.car_dealer.controller.CarDataController;
import pl.sdacademy.spring.car_dealer.controller.SellingController;
import pl.sdacademy.spring.car_dealer.repository.*;
import pl.sdacademy.spring.car_dealer.service.CarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultCarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultSellingService;
import pl.sdacademy.spring.car_dealer.service.SellingService;

@Configuration
public class ApplicationConfiguration {

    @Bean("hardDriveCustomerRepository")
    public HardDriveCustomerRepository hardDriveCustomerRepository() {
        return new HardDriveCustomerRepository("customers.ser");
    }

    @Bean
    public HardDriveVehicleRepository HardDriveVehicleRepository() {
        return new HardDriveVehicleRepository("vehicles.ser");
    }

    @Bean
    public HardDrivePurchaseRepository hardDrivePurchaseRepository() {
        return new HardDrivePurchaseRepository("purchases.ser");
    }

    @Bean
    public DefaultCarDataService defaultCarDataService(VehicleRepository vehicleRepository) {
        return new DefaultCarDataService(vehicleRepository);
    }

    @Bean
    public DefaultSellingService defaultSellingService(VehicleRepository vehicleRepository, CustomerRepository customerRepository, PurchaseRepository purchaseRepository) {
        return new DefaultSellingService(vehicleRepository, customerRepository, purchaseRepository);
    }

    @Bean
    public CarDataController carDataController(CarDataService carDataService) {
        CarDataController carDataController = new CarDataController();
        carDataController.setCarDataService(carDataService);
        return carDataController;
    }

    @Bean
    public SellingController sellingController(SellingService sellingService) {
        SellingController sellingController = new SellingController();
        sellingController.setSellingService(sellingService);
        return sellingController;
    }

    @Bean
    public Application application(SellingController sellingController, CarDataController carDataController) {
        Application application = new Application();
        application.setCarDataController(carDataController);
        application.setSellingController(sellingController);
        return application;
    }
}
