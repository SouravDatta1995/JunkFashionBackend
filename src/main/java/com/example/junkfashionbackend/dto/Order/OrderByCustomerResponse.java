package com.example.junkfashionbackend.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderByCustomerResponse {
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private List<OrderResponse> orderResponses;
    private String orderState;
}

