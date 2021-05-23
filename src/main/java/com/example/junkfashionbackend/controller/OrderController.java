package com.example.junkfashionbackend.controller;

import com.example.junkfashionbackend.dto.Order.*;
import com.example.junkfashionbackend.model.Customer;
import com.example.junkfashionbackend.model.Order;
import com.example.junkfashionbackend.model.Product;
import com.example.junkfashionbackend.repository.CustomerRepository;
import com.example.junkfashionbackend.repository.OrderRepository;
import com.example.junkfashionbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/createOrder")
    private ResponseEntity<String> createOrder(@RequestBody ArrayList<Order> orders) {
        orders.forEach(order -> {
            order.setId(null);
            Product updatedProduct = productRepository.findProductByProductName(order.getProduct().getProductName());
            Customer updatedCustomer =
                    customerRepository.findCustomerByCustomerPhone(order.getCustomer().getCustomerPhone());
            order.setProduct(updatedProduct);
            order.setCustomer(updatedCustomer);
            order.setOrderState("Ordered");
            orderRepository.save(order);
            updatedProduct.setStock(updatedProduct.getStock() - order.getProductCount());
            productRepository.save(updatedProduct);
        });
        return ResponseEntity.of(Optional.of("New Order Added"));
    }

    @PostMapping("/byDate")
    private ArrayList<OrderHistoryByDateResponse> ordersByDate(@RequestBody OrderByDateRequest request) {
        ArrayList<OrderHistoryByDateResponse> ordersResult = new ArrayList<>();

        HashMap<String, ArrayList<Order>> ordersMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
        orderRepository.findAllByOrderDateBetween(LocalDateTime.parse(request.getFromDate(), formatter),
                LocalDateTime.parse(request.getToDate(), formatter)).forEach(order ->
                ordersMap.computeIfAbsent(order.getOrderDate().format(dateFormatter), ol -> new ArrayList<>()).add(order));

        ordersMap.forEach((s, orders) -> {
            OrderHistoryByDateResponse orderHistoryByDateResponse = new OrderHistoryByDateResponse();
            orderHistoryByDateResponse.setOrderDate(s);
            HashMap<String, ArrayList<OrderResponse>> customerOrders = new HashMap<>();
            orderHistoryByDateResponse.setTotalProfit(
                    orders.stream().mapToDouble(value ->
                            value.getProductCount() * (value.getProduct().getSellingPrice() - value.getProduct().getProductPrice()))
                            .sum());
            orders.forEach(order -> {
                OrderResponse orderResponse = new OrderResponse();
                Product p = order.getProduct();
                orderResponse.setProduct(p);
                orderResponse.setOrderId(order.getId());
                orderResponse.setProductCount(order.getProductCount());
                double profit = order.getProductCount() * (p.getSellingPrice() - p.getProductPrice());
                orderResponse.setProfit(profit);
                customerOrders.computeIfAbsent(order.getCustomer().getCustomerPhone(), s1 -> new ArrayList<>()).add(orderResponse);
            });
            List<OrderByCustomerResponse> orderByCustomerResponses = new ArrayList<>();
            customerOrders.forEach((s1, orderResponses) -> {
                OrderByCustomerResponse orderByCustomerResponse = new OrderByCustomerResponse();
                orderByCustomerResponse.setCustomerPhone(s1);
                Customer customer = customerRepository.findCustomerByCustomerPhone(s1);
                orderByCustomerResponse.setCustomerName(customer.getCustomerName());
                orderByCustomerResponse.setCustomerAddress(customer.getCustomerAddress());
                orderByCustomerResponse.setOrderResponses(orderResponses);
                orderByCustomerResponse.setOrderState(orders.get(0).getOrderState());
                orderByCustomerResponses.add(orderByCustomerResponse);

            });
            orderHistoryByDateResponse.setCustomerOrders(orderByCustomerResponses);
            ordersResult.add(orderHistoryByDateResponse);
        });

        return ordersResult;
    }

    @PostMapping("/updateOrderState")
    private ResponseEntity<String> updateOrderState(@RequestBody OrderStateUpdateRequest orderStateUpdateRequest) {
        AtomicInteger count = new AtomicInteger();
        orderRepository.findAllById(orderStateUpdateRequest.getOrderIds()).forEach(order -> {
            count.incrementAndGet();
            order.setOrderState(orderStateUpdateRequest.getOrderState());
            orderRepository.save(order);
        });
        return count.get() > 0
                ? ResponseEntity.of(Optional.of("Order State Updated"))
                : ResponseEntity.of(Optional.of("Order not found"));
    }
}
