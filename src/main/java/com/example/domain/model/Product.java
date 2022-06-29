package com.example.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import javax.validation.constraints.DecimalMax;
import java.math.BigDecimal;
import java.util.List;
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
    @Column(name = "product_id")
    private UUID productId;
    private String name;

    @DecimalMax("999999")
    private BigDecimal price;

    private Integer inventoryAmount;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "categoryId"
    )
    @JsonIgnore
    private Category category;

    public Product(){

    }

    public Product(
                   String name,
                   BigDecimal price,
                   Integer inventoryAmount) {
        this.name = name;
        this.price= price;
        this.inventoryAmount = inventoryAmount;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getInventoryAmount() {
        return inventoryAmount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", inventoryAmount=" + inventoryAmount +
                '}';
    }
}
