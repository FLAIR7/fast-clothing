package com.example.controller;

import com.example.domain.dto.order.OrderRequest;
import com.example.domain.dto.order.OrderResponse;
import com.example.domain.mapper.OrderMapper;
import com.example.domain.model.*;
import com.example.domain.repository.CategoryRepository;
import com.example.domain.repository.OrdersRepository;
import com.example.domain.repository.ProductRepository;
import com.example.domain.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        List<OrderResponse> orders = ordersRepository.findAll().stream().map(OrderMapper::toOrderResponse)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest request) {
        Orders order = OrderMapper.toOrder(request);
        Orders orderToSave = orderService.saveOrder(order);
        OrderResponse response = OrderMapper.toOrderResponse(orderToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
