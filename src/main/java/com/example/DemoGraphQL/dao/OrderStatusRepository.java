package com.example.DemoGraphQL.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DemoGraphQL.model.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

}