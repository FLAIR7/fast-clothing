package com.example.controller;

import com.example.domain.dto.order.OrdersRequest;
import com.example.domain.dto.order.OrdersResponse;
import com.example.domain.mapper.OrdersMapper;
import com.example.domain.model.*;
import com.example.domain.repository.OrdersRepository;
import com.example.domain.repository.ProductRepository;
import com.example.domain.repository.UserRepository;
import com.example.domain.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    private final OrdersRepository repository;
    private final OrdersService service;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrdersController(OrdersRepository repository,
                            OrdersService service,
                            ProductRepository productRepository,
                            UserRepository userRepository){
        this.repository = repository;
        this.service = service;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<OrdersResponse>> findAll(){
        List<OrdersResponse> orders = repository
                .findAll()
                .stream()
                .map(OrdersMapper::toOrderResponse)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping
    public ResponseEntity<OrdersResponse> save(@Valid @RequestBody OrdersRequest request){
        List<Product> products = (List<Product>) productRepository.findAllById(request.getProductsId());
        User user = userRepository.findByEmailFetchRoles(request.getEmail()).orElse(null);
        Orders order = OrdersMapper.toOrder(request);
        order.setProductList(products);
        order.setUser(user);
        Orders orderSaved = service.saveOrder(order);
        OrdersResponse response = OrdersMapper.toOrderResponse(orderSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID orderId){
        service.deleteById(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
