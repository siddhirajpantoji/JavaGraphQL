package com.example.DemoGraphQL.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.DemoGraphQL.model.FixedOrder;

public interface OrderRepository extends JpaRepository<FixedOrder, Long> , JpaSpecificationExecutor<FixedOrder>{
	
}