package com.example.domain.dto.order;

import com.example.domain.dto.product.ProductRequest;
import com.example.domain.model.Payment;
import com.example.domain.model.Product;
import com.example.domain.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class OrderRequest {

    private List<UUID> productsId;

    @NotNull
    private UUID userId;

    private Payment method;

    public List<UUID> getProducts() {
        return productsId;
    }

    public void setProducts(List<UUID> products) {
        this.productsId = products;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Payment getMethod() {
        return method;
    }

    public void setMethod(Payment method) {
        this.method = method;
    }
}
