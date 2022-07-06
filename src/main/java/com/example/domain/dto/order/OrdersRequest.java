package com.example.domain.dto.order;

import com.example.domain.model.Payment;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrdersRequest {

    @NotNull
    private List<UUID> productsId;

    @NotNull
    private UUID userId;

    @NotNull
    private Payment method;

    public OrdersRequest(List<UUID> productsId, UUID userId, Payment method) {
        this.productsId = productsId;
        this.userId = userId;
        this.method = method;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersRequest that = (OrdersRequest) o;
        return productsId.equals(that.productsId) && userId.equals(that.userId) && method == that.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productsId, userId, method);
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "productsId=" + productsId +
                ", userId=" + userId +
                ", method=" + method +
                '}';
    }
}
