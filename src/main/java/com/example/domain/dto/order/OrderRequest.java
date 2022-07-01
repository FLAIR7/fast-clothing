package com.example.domain.dto.order;

import com.example.domain.dto.product.ProductRequest;
import com.example.domain.model.Payment;
import com.example.domain.model.Product;
import com.example.domain.model.User;

import java.util.List;
import java.util.UUID;

public class OrderRequest {

    private List<ProductRequest> products;

    private UUID userId;

    private Payment method;

}
