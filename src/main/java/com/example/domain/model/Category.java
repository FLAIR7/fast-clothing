package com.example.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Category {

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
    @ColumnDefault("random_uuid()")
    private  UUID categoryId;

    private  String name;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private List<Product> products = new ArrayList<>();

    public Category(){

    }

    public Category(String name){
        this.name = name;
    }

    public Category(
                    String name,
                    List<Product> products
                    ){
        this.name = name;
        this.products = products;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
