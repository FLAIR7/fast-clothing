package com.example.domain.repository;

import com.example.domain.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveOrder(){
        User user = new User("diego@gmail.com", "diego123");
        Product product1 = new Product("TV", new BigDecimal(1.5), 5);
        Product product2 = new Product("IPHONE", new BigDecimal(1005), 3);

        Category category = new Category("eletronicos");
        categoryRepository.save(category);
        product1.setCategory(category);
        product2.setCategory(category);
        productRepository.save(product1);
        productRepository.save(product2);

        Orders order = new Orders(List.of(product1, product2), user, Payment.DEBIT);
        orderRepository.save(order);
        System.out.println(orderRepository.findAll());
    }

}