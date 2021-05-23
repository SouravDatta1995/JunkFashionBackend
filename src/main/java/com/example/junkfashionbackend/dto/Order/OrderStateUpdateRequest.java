package com.example.junkfashionbackend.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStateUpdateRequest {
    private List<BigInteger> orderIds;
    private String orderState;
}
