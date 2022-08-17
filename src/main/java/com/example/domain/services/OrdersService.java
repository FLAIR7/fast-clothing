package com.example.domain.services;

import com.example.exceptions.NotFoundException;
import com.example.domain.model.Orders;
import com.example.domain.model.User;
import com.example.domain.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {

    private final OrdersRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrdersService(OrdersRepository orderRepository,
                         UserService userService){
        this.orderRepository = orderRepository;
        this.userService = userService;
       
    }

    @Transactional(readOnly = true)
    public Optional<Orders> findById(UUID orderId){
        return orderRepository.findById(orderId);
    }

    @Transactional
    public Orders saveOrder(Orders orders){
        Optional<User> optUser = userService.findById(orders.getUser().getUserId());

        if(optUser.isEmpty()){
            throw new NotFoundException("User does not exist");
        }

        return orderRepository.save(orders);
    }

    @Transactional
    public void deleteById(UUID orderId){
        Optional<Orders> optOrders = orderRepository.findById(orderId);

        if(optOrders.isEmpty()){
            throw new NotFoundException("Order not found");
        }

        orderRepository.deleteById(orderId);
    }

}
