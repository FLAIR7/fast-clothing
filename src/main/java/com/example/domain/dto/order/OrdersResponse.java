package com.example.domain.dto.order;

import com.example.domain.dto.user.UserResponse;
import com.example.domain.model.Product;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrdersResponse {

    private UUID ordersId;

    private List<Product> products;

    private UserResponse user;

    public OrdersResponse() {
    }

    public OrdersResponse(UUID ordersId,
                          List<Product> products,
                          UserResponse user) {
        this.ordersId = ordersId;
        this.products = products;
        this.user = user;
    }

    public UUID getOrdersId() {
        return ordersId;
    }

    public void setOrderId(UUID ordersId) {
        this.ordersId = ordersId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersResponse that = (OrdersResponse) o;
        return ordersId.equals(that.ordersId) && products.equals(that.products) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordersId, products, user);
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId=" + ordersId +
                ", products=" + products +
                ", user=" + user +
                '}';
    }
}
