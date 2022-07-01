package com.example.controller;

import com.example.domain.model.Category;
import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public String getAllProducts(){
        Product product = new Product("TV", BigDecimal.valueOf(1500), 5);
        Category category = new Category("Eletronicos");
        product.setCategory(category);
        repository.save(product);
        return "Hello, World";
    }
}
