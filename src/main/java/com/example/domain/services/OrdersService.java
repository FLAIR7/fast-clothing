package com.example.domain.services;

import com.example.domain.exceptions.NotFoundException;
import com.example.domain.model.Orders;
import com.example.domain.model.User;
import com.example.domain.repository.OrdersRepository;
import com.example.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {

    private final OrdersRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrdersService(OrdersRepository orderRepository,
                         UserService userService,
                         ProductService productService){
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public Optional<Orders> findById(UUID orderId){
        return orderRepository.findById(orderId);
    }

    public Orders saveOrder(Orders orders){
        Optional<User> optUser = userService.findById(orders.getUser().getUserId());

        if(optUser.isEmpty()){
            throw new NotFoundException("User does not exist");
        }

        return orderRepository.save(orders);
    }

}
