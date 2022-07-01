package com.example.domain.dto.product;

import com.example.domain.model.Category;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductResponse {

    private UUID productId;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    private Category category;
}
