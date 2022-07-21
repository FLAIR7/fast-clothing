package com.example.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Orders {

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
//    @ColumnDefault("random_uuid()")
    public UUID ordersId;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Product> products;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "userId"
    )
    private User user;

    private Payment paymentMethod;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public Orders(){

    }

    public Orders(List<Product> products,
                  User user,
                  Payment paymentMethod) {
        this.products = products;
        this.user = user;
        this.paymentMethod = paymentMethod;
    }

    public Orders(UUID ordersId,
                  List<Product> products,
                  User user,
                  Payment paymentMethod) {
        this.ordersId = ordersId;
        this.products = products;
        this.user = user;
        this.paymentMethod = paymentMethod;
    }

    public UUID getOrdersId() {
        return ordersId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProductList(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return ordersId.equals(orders.ordersId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordersId);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "products=" + products +
                ", user=" + user +
                ", paymentMethod=" + paymentMethod +
                ", timestamp=" + timestamp +
                '}';
    }
}
