package com.example.domain.dto.product;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ProductResponse {

    private UUID productId;

    private String name;

    private BigDecimal price;

    public ProductResponse() {

    }

    public ProductResponse(UUID productId,
                           String name,
                           BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponse that = (ProductResponse) o;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
