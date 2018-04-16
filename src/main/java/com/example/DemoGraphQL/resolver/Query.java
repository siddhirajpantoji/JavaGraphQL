package com.example.DemoGraphQL.resolver;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.dao.FixedOrderSpecification;
import com.example.DemoGraphQL.dao.OrderRepository;
import com.example.DemoGraphQL.dao.SearchCriteria;
import com.example.DemoGraphQL.model.FixedOrder;

@Service
public class Query implements GraphQLQueryResolver {

	@Autowired
	private OrderRepository orderRepository;


	public Iterable<FixedOrder> getOrdersBy(Long id, String status, String senderId, String beneficiaryId) {

		List<FixedOrderSpecification> specs = new ArrayList<>();

		if (null != id) {

			// Add Order Id Query
			specs.add(new FixedOrderSpecification(new SearchCriteria("id", ":", id)));
		}
		if (StringUtils.isNotEmpty(status)) {
			specs.add(new FixedOrderSpecification(new SearchCriteria("status", ":", status)));
		}
		if (StringUtils.isNotEmpty(senderId)) {
			specs.add(new FixedOrderSpecification(new SearchCriteria("senderId", ":", senderId)));
		}
		if (StringUtils.isNotEmpty(beneficiaryId)) {
			specs.add(new FixedOrderSpecification(new SearchCriteria("beneficiaryId", ":", beneficiaryId)));
		}

		if (CollectionUtils.isEmpty(specs)) {
			return orderRepository.findAll();
		} else {
			Specification<FixedOrder> result = specs.get(0);
			for (int i = 1; i < specs.size(); i++) {
				result = Specifications.where(result).and(specs.get(i));
			}
			return orderRepository.findAll(result);
		}
	}

	public long getOrderCount(Long id, String status, String senderId, String beneficiaryId) {

		List<FixedOrderSpecification> specs = new ArrayList<>();

		if (null != id) {

			// Add Order Id Query
			specs.add(new FixedOrderSpecification(new SearchCriteria("id", ":", id)));
		}
		if (StringUtils.isNotEmpty(status)) {
			specs.add(new FixedOrderSpecification(new SearchCriteria("status", ":", status)));
		}
		if (StringUtils.isNotEmpty(senderId)) {
			specs.add(new FixedOrderSpecification(new SearchCriteria("senderId", ":", senderId)));
		}
		if (StringUtils.isNotEmpty(beneficiaryId)) {
			specs.add(new FixedOrderSpecification(new SearchCriteria("beneficiaryId", ":", beneficiaryId)));
		}

		if (CollectionUtils.isEmpty(specs)) {
			return orderRepository.count();
		} else {
			Specification<FixedOrder> result = specs.get(0);
			for (int i = 1; i < specs.size(); i++) {
				result = Specifications.where(result).and(specs.get(i));
			}
			return orderRepository.count(result);
		}
	}
}
