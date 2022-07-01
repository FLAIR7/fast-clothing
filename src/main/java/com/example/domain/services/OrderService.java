package com.example.domain.services;

import com.example.Exceptions.NotFoundException;
import com.example.domain.model.Orders;
import com.example.domain.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository repository;

    public Orders saveOrder(Orders orders){
        Optional<Orders> optOrders = repository.findById(orders.getOrdersId());

        if(optOrders.isEmpty()){
            throw new NotFoundException("Order not found");
        }

        return repository.save(orders);
    }

}
