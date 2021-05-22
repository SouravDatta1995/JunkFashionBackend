package com.example.junkfashionbackend.repository;

import com.example.junkfashionbackend.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductRepository")
public interface ProductRepository extends MongoRepository<Product, Long> {

    List<Product> findByProductName(String name);
    List<Product> findByProductNameContainingIgnoreCase(String name);
}
