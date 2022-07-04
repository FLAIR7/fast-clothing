package com.example.domain.dto.order;

import com.example.domain.dto.user.UserResponse;
import com.example.domain.model.Product;
import java.util.List;
import java.util.UUID;

public class OrderResponse {

    private UUID orderId;

    private List<Product> products;

    private UserResponse user;

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
