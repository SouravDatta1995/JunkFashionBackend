package com.example.junkfashionbackend.repository;

import com.example.junkfashionbackend.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository("CustomerRepository")
public interface CustomerRepository extends MongoRepository<Customer, BigInteger> {

    List<Customer> findCustomersByCustomerPhone(String phoneNumber);


    List<Customer> findTop5CustomersByCustomerPhoneContainingOrCustomerNameContainingIgnoreCase(String customerPhone, String customerName);

    Customer findCustomerByCustomerPhone(String customerPhone);
}
