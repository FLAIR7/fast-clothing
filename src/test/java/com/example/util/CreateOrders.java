package com.example.util;

import com.example.domain.model.Orders;
import com.example.domain.model.Payment;

import java.util.UUID;

public class CreateOrders {

    public static Orders createOrderWithId(){
        return new Orders(
                UUID.fromString( "d22f10ff-f7b2-4188-b652-946d7ba4361c"),
                CreateProduct.createListProduct(),
                CreateUser.createUserWithId(),
                Payment.MONEY
        );
    }

}
