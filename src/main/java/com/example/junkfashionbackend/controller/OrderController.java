package com.example.junkfashionbackend.controller;

import com.example.junkfashionbackend.model.Customer;
import com.example.junkfashionbackend.model.Order;
import com.example.junkfashionbackend.model.Product;
import com.example.junkfashionbackend.repository.CustomerRepository;
import com.example.junkfashionbackend.repository.OrderRepository;
import com.example.junkfashionbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/createOrder")
    private ResponseEntity<String> createOrder(@RequestBody ArrayList<Order> orders) {
        orders.forEach(order -> {
            order.setId(null);
            orderRepository.save(order);
            Product updatedProduct=order.getProduct();
            updatedProduct.setStock(updatedProduct.getStock() - order.getProductCount());
            productRepository.save(updatedProduct);
        });
        return ResponseEntity.of(Optional.of("New Order Added"));
    }
}
