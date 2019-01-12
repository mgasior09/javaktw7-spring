package pl.sdacademy.spring.car_dealer;

import pl.sdacademy.spring.car_dealer.controller.CarDataController;
import pl.sdacademy.spring.car_dealer.controller.SellingController;
import pl.sdacademy.spring.car_dealer.repository.*;
import pl.sdacademy.spring.car_dealer.service.CarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultCarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultSellingService;
import pl.sdacademy.spring.car_dealer.service.SellingService;

import java.util.Scanner;

public class Application {

    private CarDataController carDataController = null;
    private SellingController sellingController = null;

    public void initialize() {
        CustomerRepository customerRepository = new HardDriveCustomerRepository();
        PurchaseRepository purchaseRepository = new HardDrivePurchaseRepository();
        VehicleRepository vehicleRepository = new HardDriveVehicleRepository();
        CarDataService carDataService = new DefaultCarDataService();
        carDataService.setVehicleRepository(vehicleRepository);
        SellingService sellingService = new DefaultSellingService();
        sellingService.setVehicleRepository(vehicleRepository);
        sellingService.setCustomerRepository(customerRepository);
        sellingService.setPurchaseRepository(purchaseRepository);
        carDataController = new CarDataController();
        carDataController.setCarDataService(carDataService);
        sellingController = new SellingController();
        sellingController.setSellingService(sellingService);
    }

    public void start() {
        Long choice = -1L;
        while (choice != 9L) {
            printMenu();
            choice = readInput();
            switch (choice.intValue()) {
                case 1:
                    carDataController.printAvailableCars();
                    break;
                case 2:
                    System.out.print("Which car you want to sell? ");
                    Long vehicleId = readInput();
                    sellingController.buyVehicle(vehicleId);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }

    }

    private void printMenu() {
        System.out.println();
        System.out.println();
        System.out.println("Welcome in Car Dealer application! What you want to do?");
        System.out.println("1) Show Vehicles");
        System.out.println("2) Sell Vehicle");
        System.out.println("9) Exit");
        System.out.print("What is your choice? ");
    }

    private Long readInput() {
        try {
            return Long.parseLong(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            return -1L;
        }
    }
}
