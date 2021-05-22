package com.example.junkfashionbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Component
@Document(collection = "order")
public @Data  class Order {
    @Id
    BigInteger id;
    @DBRef
    private Customer customer;
    @DBRef
    private Product product;
    private int productCount;
    private double sellingPrice;
    private Date orderDate;
}
