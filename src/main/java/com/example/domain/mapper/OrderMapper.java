package com.example.domain.mapper;

import com.example.domain.dto.order.OrderRequest;
import com.example.domain.dto.order.OrderResponse;
import com.example.domain.model.Orders;
import org.modelmapper.ModelMapper;

public class OrderMapper {

    public static Orders toOrder(OrderRequest request){
        return new ModelMapper().map(request, Orders.class);
    }

    public static OrderResponse toOrderResponse(Orders order){
        return new ModelMapper().map(order, OrderResponse.class);
    }
}
