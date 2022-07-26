package com.example.util;

import com.example.domain.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateProduct {

    public static Product createProduct(){
        return new Product(
                "TV",
                BigDecimal.valueOf(1500),
                5
        );
    }

    public static Product createProductWithId(){
        return new Product(
                UUID.fromString(
                "5987242e-2926-4ae2-a05a-ae294de4db8e"),
                "TV",
                BigDecimal.valueOf(1500),
                5
        );
    }

    public static List<Product> createListProduct(){
        List<Product> products = new ArrayList<>();
        products.add(createProductWithId());
        products.add(new Product(
                UUID.fromString("4bf9a55a-d2a7-49a8-8d08-1b15fce7ca93"),
                "IPHONE",
                BigDecimal.valueOf(1000),
                1));
        return products;
    }

}
