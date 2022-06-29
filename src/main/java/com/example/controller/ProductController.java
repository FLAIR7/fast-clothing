package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin("*")
public class ProductController {

    @GetMapping
    public String getAllProducts(){
        return "Hello, World";
    }
}
