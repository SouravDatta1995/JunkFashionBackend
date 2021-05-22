package com.example.junkfashionbackend.controller;

import com.example.junkfashionbackend.model.Customer;
import com.example.junkfashionbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/add")
    private ResponseEntity<String> createNewCustomer(@RequestBody Customer customer) {
        if (customerRepository.findCustomersByCustomerPhone(customer.getCustomerPhone()).size() > 0)
            return ResponseEntity.of(Optional.of("Customer already added"));
        else {
            customer.setId(null);
            customerRepository.save(customer);
            return ResponseEntity.of(Optional.of("New Customer Added"));
        }
    }


    @PostMapping("/search")
    private List<Customer> findCustomerByNameAndPhoneNumber(@RequestBody Customer customer) {
        return customerRepository.findTop5CustomersByCustomerPhoneContainingOrCustomerNameContainingIgnoreCase(
                customer.getCustomerPhone(), customer.getCustomerName());
    }

    @GetMapping
    private List<Customer> findAllCustomer(){
        return customerRepository.findAll();
    }

}
