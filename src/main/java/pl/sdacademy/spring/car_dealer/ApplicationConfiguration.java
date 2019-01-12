package pl.sdacademy.spring.car_dealer;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import pl.sdacademy.spring.car_dealer.controller.CarDataController;
import pl.sdacademy.spring.car_dealer.controller.SellingController;
import pl.sdacademy.spring.car_dealer.repository.*;
import pl.sdacademy.spring.car_dealer.service.DefaultCarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultSellingService;

@Configuration
@PropertySource("application.properties")
public class ApplicationConfiguration {

    @Value("${repository.customer.hardDrive.fileLocation}")
    private String customerRepoLocation;
    @Value("${repository.purchase.hardDrive.fileLocation}")
    private String purchaseRepoLocation;


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "hardDriveCustomerRepository", initMethod = "initialize", destroyMethod = "cleanUp")
    public HardDriveCustomerRepository hardDriveCustomerRepository() {
        return new HardDriveCustomerRepository(customerRepoLocation);
    }

    @Bean(name = "hardDriveVehicleRepository", initMethod = "initialize", destroyMethod = "cleanUp")
    public HardDriveVehicleRepository hardDriveVehicleRepository(@Value("${repository.vehicle.hardDrive.fileLocation}") String repoLocation) {
        return new HardDriveVehicleRepository(repoLocation);
    }

    @Bean(name = "hardDrivePurchaseRepository", initMethod = "initialize", destroyMethod = "cleanUp")
    public HardDrivePurchaseRepository hardDrivePurchaseRepository() {
        return new HardDrivePurchaseRepository(purchaseRepoLocation);
    }

    @Bean
    public DefaultCarDataService defaultCarDataService(VehicleRepository vehicleRepository) {
        return new DefaultCarDataService(vehicleRepository);
    }

    @Bean(name = "defaultSellingService")
    public DefaultSellingService defaultSellingService(VehicleRepository vehicleRepository, CustomerRepository customerRepository, PurchaseRepository purchaseRepository) {
        return new DefaultSellingService(vehicleRepository, customerRepository, purchaseRepository);
    }

    @Bean(name = "carDataController", autowire = Autowire.BY_TYPE)
    public CarDataController carDataController() {
        return new CarDataController();

    }

    @Bean(name = "sellingController", autowire = Autowire.BY_TYPE)
    public SellingController sellingController() {
        return new SellingController();
    }

    @Bean(name = "application", autowire = Autowire.BY_TYPE)
    public Application application() {
        return new Application();
    }
}
