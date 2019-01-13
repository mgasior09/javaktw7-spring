package pl.sdacademy.spring.car_dealer.repository;

import pl.sdacademy.spring.car_dealer.model.Customer;

import java.util.List;

public interface CustomerFinder {
    List<Customer> searchByLastName(String lastName);
    List<Customer> searchByFirstOrLastName(String name);
}
