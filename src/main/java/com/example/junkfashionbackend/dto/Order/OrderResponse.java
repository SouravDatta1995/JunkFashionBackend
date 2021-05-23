package com.example.junkfashionbackend.dto.Order;

import com.example.junkfashionbackend.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Product product;
    private int productCount;
    private double profit;
    private BigInteger orderId;
}
