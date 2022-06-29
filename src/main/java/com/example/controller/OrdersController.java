package com.example.controller;

import com.example.domain.model.*;
import com.example.domain.repository.CategoryRepository;
import com.example.domain.repository.OrdersRepository;
import com.example.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/")
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String getAllOrders(){
        return "Orders";
    }
}
