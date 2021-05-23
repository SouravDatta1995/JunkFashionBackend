package com.example.junkfashionbackend.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryByDateResponse {

    private String orderDate;
    private List<OrderByCustomerResponse> customerOrders;
    private double totalProfit;
}
