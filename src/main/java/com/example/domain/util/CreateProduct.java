package com.example.domain.util;

import com.example.domain.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProduct {

    public static Product createProductWithId(){
        return new Product(UUID.fromString(
                "5987242e-2926-4ae2-a05a-ae294de4db8e"),
                "TV",
                BigDecimal.valueOf(1500),
                5);
    }

}
