package com.example.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY, generator = "uuid2"
    )
    @GenericGenerator(
            name = "uuid2", strategy = "uuid2"
    )
    @Type(
            type = "uuid-char"
    )
//    @Column(name = "product_id")
    private UUID productId;
    @NotBlank
    private String name;

    @DecimalMax("999999")
    private BigDecimal price;

    private Integer quantity;

    public Product(){

    }

    public Product(
                   String name,
                   BigDecimal price,
                   Integer quantity) {
        this.name = name;
        this.price= price;
        this.quantity = quantity;
    }

    public Product(UUID productId,
            String name,
            BigDecimal price,
            Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price= price;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void increaseInventoryAmount(Integer value){
        this.quantity += value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId) && name.equals(product.name) && price.equals(product.price) && quantity.equals(product.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, price, quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
