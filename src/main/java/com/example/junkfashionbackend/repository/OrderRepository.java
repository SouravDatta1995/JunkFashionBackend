package com.example.junkfashionbackend.repository;

import com.example.junkfashionbackend.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository("OrderRepository")
public interface OrderRepository extends MongoRepository<Order, BigInteger> {
}
