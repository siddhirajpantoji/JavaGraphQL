package com.example.DemoGraphQL.resolver;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.DemoGraphQL.model.FixedOrder;

@Component
public class OrderResolver implements GraphQLResolver<FixedOrder> {
	
}