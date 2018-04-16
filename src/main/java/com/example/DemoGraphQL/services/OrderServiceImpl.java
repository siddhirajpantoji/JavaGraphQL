package com.example.DemoGraphQL.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.DemoGraphQL.dao.FixedOrderSpecification;
import com.example.DemoGraphQL.dao.OrderRepository;
import com.example.DemoGraphQL.dao.OrderStatusRepository;
import com.example.DemoGraphQL.dao.SearchCriteria;
import com.example.DemoGraphQL.exception.OrderNotFoundException;
import com.example.DemoGraphQL.model.FixedOrder;
import com.example.DemoGraphQL.model.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderStatusRepository orderStatusRepository;

	private static final Float rate = 2.3f;

	@Override
	public Iterable<FixedOrder> getOrdersBy(Long id, String status, String senderId, String beneficiaryId) {
		// TODO Auto-generated method stub
		Specification<FixedOrder> specs = getQueryList(id, status, senderId, beneficiaryId);
		if (null == specs) {
			return orderRepository.findAll();
		} else {
			return orderRepository.findAll(specs);
		}
	}

	@Override
	public long getOrderCount(Long id, String status, String senderId, String beneficiaryId) {
		// TODO Auto-generated method stub
		Specification<FixedOrder> specs = getQueryList(id, status, senderId, beneficiaryId);
		if (null == specs) {
			return orderRepository.count();
		} else {
			return orderRepository.count(specs);
		}
	}

	@Override
	public FixedOrder getNewOrder(String baseCurrency, String quoteCurrency, Float baseAmount, String senderId,
			String beneficiaryId, String purpose) {
		// TODO Auto-generated method stub
		FixedOrder o = new FixedOrder();
		o.setBaseCurrency(baseCurrency);
		o.setQuoteCurrency(quoteCurrency);
		o.setBaseAmount(baseAmount);
		o.setQuoteAmount(baseAmount * rate);
		o.setSenderId(senderId);
		o.setBeneficiaryId(beneficiaryId);
		o.setStatus("CREATED");
		o.setPurpose(purpose);
		o.setRate(rate);
		o = orderRepository.save(o);
		OrderStatus status = new OrderStatus();
		status.setOrder(o);
		status.setStatus(o.getStatus());
		status = orderStatusRepository.save(status);
		List<OrderStatus> status1 = new ArrayList<>();
		status1.add(status);
		o.setHistory(status1);
		o = orderRepository.save(o);
		return o;
	}

	@Override
	public FixedOrder updateOrderStatus(Long id, String status) {
		FixedOrder order = orderRepository.findOne(id);
		if (null == order) {
			throw new OrderNotFoundException("The Order to be updated was found", id);
		}
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrder(order);
		orderStatus.setStatus(status);
		orderStatus = orderStatusRepository.save(orderStatus);
		order.getHistory().add(orderStatus);
		order.setStatus(status);
		order = orderRepository.save(order);
		return order;
	}

	private Specification<FixedOrder> getQueryList(Long id, String status, String senderId, String beneficiaryId) {

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
		if (!CollectionUtils.isEmpty(specs)) {
			Specification<FixedOrder> result = specs.get(0);
			for (int i = 1; i < specs.size(); i++) {
				result = Specifications.where(result).and(specs.get(i));
			}
			return result;
		} else {
			return null;
		}

	}
}
