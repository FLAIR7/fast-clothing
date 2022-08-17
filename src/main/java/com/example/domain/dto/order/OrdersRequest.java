package com.example.domain.dto.order;

import com.example.domain.model.Payment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrdersRequest {

    @NotNull
    private List<UUID> productsId;

    @NotBlank
    private String email;

    @NotNull
    private Payment method;

    public OrdersRequest(){

    }

    public OrdersRequest(List<UUID> productsId,
                         String email,
                         Payment method) {
        this.productsId = productsId;
        this.email = email;
        this.method = method;
    }

    public List<UUID> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<UUID> productsId) {
        this.productsId = productsId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Payment getMethod() {
        return method;
    }

    public void setMethod(Payment method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "productsId=" + productsId +
                ", email=" + email +
                ", method=" + method +
                '}';
    }
}
