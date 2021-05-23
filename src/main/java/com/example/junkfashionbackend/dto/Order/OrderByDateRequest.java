package com.example.junkfashionbackend.dto.Order;

import lombok.Data;

@Data
public class OrderByDateRequest {
    private String fromDate;
    private String toDate;
}
