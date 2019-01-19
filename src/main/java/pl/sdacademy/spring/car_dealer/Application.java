package pl.sdacademy.spring.car_dealer;

import org.springframework.stereotype.Component;
import pl.sdacademy.spring.car_dealer.controller.CarDataController;
import pl.sdacademy.spring.car_dealer.controller.SellingController;

import java.util.Scanner;

@Component
public class Application {

    private final CarDataController carDataController;
    private final SellingController sellingController;

    Application(CarDataController carDataController, SellingController sellingController) {
        this.carDataController = carDataController;
        this.sellingController = sellingController;
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
                case 3:
                    carDataController.addVehicle();
                    break;
                case 4:
                    sellingController.printCustomerPurchases();
                    break;
                case 5:
                    sellingController.printPurchasesFilteredByPrice();
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
        System.out.println("3) Add Vehicle");
        System.out.println("4) Find customer purchases");
        System.out.println("5) Print purchases by price");
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
