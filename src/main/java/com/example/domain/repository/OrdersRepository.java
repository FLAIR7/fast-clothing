package com.example.domain.repository;

import com.example.domain.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {

}
