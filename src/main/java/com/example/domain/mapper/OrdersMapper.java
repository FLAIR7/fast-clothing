package com.example.domain.mapper;

import com.example.domain.dto.order.OrdersRequest;
import com.example.domain.dto.order.OrdersResponse;
import com.example.domain.model.Orders;
import org.modelmapper.ModelMapper;

public class OrdersMapper {

    public static Orders toOrder(OrdersRequest request){
        return new ModelMapper().map(request, Orders.class);
    }

    public static OrdersResponse toOrderResponse(Orders order){
        return new ModelMapper().map(order, OrdersResponse.class);
    }
}
