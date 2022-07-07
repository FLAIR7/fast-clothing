package com.example.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Orders Repository Tests")
class OrdersRepositoryTest {

    private final OrdersRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    @Autowired
    public OrdersRepositoryTest(OrdersRepository ordersRepository,
                                ProductRepository productRepository,
                                UserRepository userRepository) {
        this.orderRepository = ordersRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Test
    public void saveOrder_ReturnOrder_WhenSuccessful(){
    
    }

}