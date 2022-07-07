package com.example.domain.services;

import com.example.domain.model.Orders;
import com.example.domain.repository.OrdersRepository;
import com.example.domain.repository.ProductRepository;
import com.example.domain.repository.UserRepository;
import com.example.util.CreateOrders;
import com.example.util.CreateUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrdersServiceTest {

    @InjectMocks
    OrdersService service;

    @Mock
    OrdersRepository repository;

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){
        Mockito.when(userService.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(CreateUser.createUserWithId()));
        Mockito.when(userRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(CreateUser.createUserWithId()));
        Mockito.when(repository.save(ArgumentMatchers.any(Orders.class))).thenReturn(CreateOrders.createOrderWithId());
    }

    @Test
    void saveOrders_ReturnOrder_WhenSuccessuful(){
        Orders orders = service.saveOrder(CreateOrders.createOrderWithId());
        Assertions.assertThat(orders).isNotNull();
        Assertions.assertThat(orders.getOrdersId()).isNotNull();
    }

}