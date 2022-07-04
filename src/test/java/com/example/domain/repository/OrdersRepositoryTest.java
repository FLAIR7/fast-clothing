package com.example.domain.repository;

import com.example.domain.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class OrdersRepositoryTest {

    private final OrdersRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrdersRepositoryTest(OrdersRepository ordersRepository,
                                ProductRepository productRepository,
                                UserRepository userRepository)
    {
        this.orderRepository = ordersRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @AfterEach
    public void tearDown(){
        userRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void saveOrder(){
        User user = new User("diego@gmail.com", "diego123");
        Product product1 = new Product("TV", new BigDecimal(15), 5);
        Product product2 = new Product("IPHONE", new BigDecimal(1005), 3);
        Orders order = new Orders(List.of(product1, product2), user, Payment.MONEY);
        Orders orderSaved = orderRepository.save(order);
        assertThat(orderSaved).isNotNull();
    }



}