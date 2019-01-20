package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.model.PurchaseFormData;
import pl.sdacademy.spring.car_dealer.service.SellingService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
@RequestMapping("/purchases")
public class SellingController {

    private final SellingService sellingService;

    SellingController(SellingService sellingService) {
        this.sellingService = sellingService;
    }


    public void buyVehicle(Long vehicleId) {
        Customer customer = getCustomerData();
        Long customerPrice = getCustomerPrice();
        sellingService.sell(vehicleId, customer, customerPrice);
    }

    private Customer getCustomerData() {
        Customer customer = new Customer();
        System.out.println("Provide customer data:");
        System.out.print("   Name: ");
        customer.setName(readInput());
        System.out.print("   Last name: ");
        customer.setSurname(readInput());
        System.out.print("   Document number: ");
        customer.setDocumentNo(readInput());
        System.out.print("   Telephone: ");
        customer.setTelephone(readInput());
        return customer;
    }

    private Long getCustomerPrice() {
        while (true) {
            try {
                System.out.print("Selling price for this customer: ");
                return Long.parseLong(readInput());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Provide again.");
            }
        }
    }

    private String readInput() {
        return new Scanner(System.in).nextLine();
    }

    public void printCustomerPurchases() {
        System.out.print(" Customer document number:");
        String documentNo = readInput();
        List<Purchase> customerPurchases = sellingService.getPurchases(documentNo);
        if (customerPurchases.isEmpty()) {
            System.out.println("No purchases made");
        } else {
            for (Purchase customerPurchase : customerPurchases) {
                System.out.println(customerPurchase.getDate() + " - " + customerPurchase.getPrice() + "PLN");
            }
        }

    }

    private Long readNumberInput() {
        try {
            return Long.parseLong(readInput());
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    public void printPurchasesFilteredByPrice() {
        System.out.println("Insert minimal price");
        Long minPrice = readNumberInput();
        System.out.println("Insert maximal price");
        Long maxPrice = readNumberInput();
        List<Purchase> purchasesBetweenMinAndMaxPrice = sellingService.getPurchases(minPrice, maxPrice);
        if (purchasesBetweenMinAndMaxPrice.isEmpty()) {
            System.out.println("No purchases made");
        } else {
            for (Purchase purchase : purchasesBetweenMinAndMaxPrice) {
                System.out.println(purchase.getDate() + " -- " + purchase.getPrice() + "PLN");
            }
        }
    }

    @RequestMapping("/{id}")
    public String getPurchase(@PathVariable("id") Long purchaseId, Model model) {
        Optional<Purchase> foundPurchase = sellingService.getById(purchaseId);
        foundPurchase.ifPresent(purchase -> model.addAttribute("purchase", purchase));
        return "purchaseDetails";
    }

    @PostMapping
    public String sellVehicle(@ModelAttribute("formData") PurchaseFormData formData) {
        Customer customer = new Customer();
        customer.setName(formData.getName());
        customer.setSurname(formData.getSurname());
        customer.setDocumentNo(formData.getDocumentNo());
        customer.setTelephone(formData.getTelephone());
        sellingService.sell(formData.getVehicleId(), customer, formData.getPrice());
        return "redirect:/vehicles";
    }

}

