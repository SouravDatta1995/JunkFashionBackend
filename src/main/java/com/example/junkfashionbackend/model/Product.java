package com.example.junkfashionbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@Document(collection = "Product")
public @Data class Product {

    @Id
    private BigInteger id;

    private String productName;
    private double productPrice;
    private double sellingPrice;
    private int stock;
}

