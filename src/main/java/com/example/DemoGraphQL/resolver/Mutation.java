package com.example.DemoGraphQL.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.DemoGraphQL.model.FixedOrder;
import com.example.DemoGraphQL.services.OrderService;

@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	OrderService orderService;

	public FixedOrder getNewOrder(String baseCurrency, String quoteCurrency, Float baseAmount, String senderId,
			String beneficiaryId, String purpose) {
		return orderService.getNewOrder(baseCurrency, quoteCurrency, baseAmount, senderId, beneficiaryId, purpose);
	}

	public FixedOrder updateOrderStatus(Long id, String status) {
		return orderService.updateOrderStatus(id, status);
	}
}
