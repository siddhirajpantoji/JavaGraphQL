package com.example.DemoGraphQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoGraphQL.constants.RESTEndpointMapper;
import com.example.DemoGraphQL.model.FixedOrder;
import com.example.DemoGraphQL.response.FixedOrderResponse;
import com.example.DemoGraphQL.services.OrderService;

@RestController
public class FixedOrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.POST, path = RESTEndpointMapper.FIXED_ORDER, produces = "application/json")
	public ResponseEntity<FixedOrderResponse> craeteOrder(@RequestBody FixedOrder fixedOrder) {
		FixedOrder dbOrder = orderService.getNewOrder(fixedOrder.getBaseCurrency(), fixedOrder.getQuoteCurrency(),
				fixedOrder.getBaseAmount(), fixedOrder.getSenderId(), fixedOrder.getBeneficiaryId(),
				fixedOrder.getPurpose());
		return new ResponseEntity<FixedOrderResponse>(new FixedOrderResponse(dbOrder), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = RESTEndpointMapper.FIXED_ORDER_ALL, produces = "application/json")
	public ResponseEntity<FixedOrderResponse> getOrder(@RequestBody FixedOrder fixedOrder) {
		Iterable<FixedOrder> dbOrder = orderService.getOrdersBy(fixedOrder.getId(), fixedOrder.getStatus(),
				fixedOrder.getSenderId(), fixedOrder.getBeneficiaryId());
		return new ResponseEntity<FixedOrderResponse>(new FixedOrderResponse(dbOrder), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = RESTEndpointMapper.FIXED_ORDER_count, produces = "application/json")
	public ResponseEntity<FixedOrderResponse> getOrderCount(@RequestBody FixedOrder fixedOrder) {
		Long count = orderService.getOrderCount(fixedOrder.getId(), fixedOrder.getStatus(), fixedOrder.getSenderId(),
				fixedOrder.getBeneficiaryId());
		return new ResponseEntity<FixedOrderResponse>(new FixedOrderResponse(count), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, path = RESTEndpointMapper.FIXED_ORDER, produces = "application/json")
	public ResponseEntity<FixedOrderResponse> updateOrder(@RequestBody FixedOrder fixedOrder) {
		FixedOrder dbOrder = orderService.updateOrderStatus(fixedOrder.getId(), fixedOrder.getStatus());
		return new ResponseEntity<FixedOrderResponse>(new FixedOrderResponse(dbOrder), HttpStatus.OK);
	}
}