package com.example.domain.repository;

import com.example.domain.model.Orders;
import com.example.domain.model.Payment;
import com.example.domain.model.Product;
import com.example.domain.model.User;
import com.example.util.CreateOrders;
import com.example.util.CreateProduct;
import com.example.util.CreateUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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
    void save_PersistOrder_WhenSuccessful(){
        User user = CreateUser.createUserWithId();
        User userSaved = userRepository.save(user);

        List<Product> products = CreateProduct.createListProduct();
        List<Product> productsSaved = productRepository.saveAll(products);

        Orders orders = CreateOrders.createOrderWithId();
        orders.setProductList(productsSaved);
        Orders ordersSaved = orderRepository.save(orders);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(productsSaved).isNotNull();
        Assertions.assertThat(ordersSaved).isNotNull();
        Assertions.assertThat(ordersSaved.getUser()).isEqualTo(userSaved);
        Assertions.assertThat(ordersSaved.getProducts()).isEqualTo(productsSaved);
    }

    @Test
    void findById_ReturnsOrders_WhenSuccessful(){
        Orders orders = CreateOrders.createOrderWithId();
        Orders ordersSaved = orderRepository.save(orders);

        Optional<Orders> optOrders = orderRepository.findById(ordersSaved.getOrdersId());

        Assertions.assertThat(optOrders).isPresent();
        Assertions.assertThat(optOrders.get().getOrdersId()).isNotNull();
    }

    @Test
    void save_UpdateOrders_WhenSuccessful(){
        Orders orders = CreateOrders.createOrderWithId();
        Orders ordersSaved = orderRepository.save(orders);
        Orders ordersUpdate;

        ordersSaved.setPaymentMethod(Payment.DEBIT);

        ordersUpdate = orderRepository.save(ordersSaved);
        Assertions.assertThat(ordersUpdate).isNotNull();
        Assertions.assertThat(ordersUpdate).isEqualTo(ordersSaved);
    }

    @Test
    void deleteById_DeleteOrders_WhenSuccessful(){
        Orders orders = CreateOrders.createOrderWithId();
        Orders ordersSaved = orderRepository.save(orders);

        orderRepository.deleteById(ordersSaved.getOrdersId());

        Optional<Orders> optOrders = orderRepository.findById(ordersSaved.getOrdersId());
        Assertions.assertThat(optOrders).isNotPresent();
        Assertions.assertThat(optOrders).isEmpty();
    }
}