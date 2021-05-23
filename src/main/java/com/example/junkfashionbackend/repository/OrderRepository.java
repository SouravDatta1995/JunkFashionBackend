package com.example.junkfashionbackend.repository;

import com.example.junkfashionbackend.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Repository("OrderRepository")
public interface OrderRepository extends MongoRepository<Order, BigInteger> {

    List<Order> findAllByOrderDateBetween(LocalDateTime minusDays, LocalDateTime now);
}
