package com.example.DemoGraphQL.response;

import org.springframework.http.HttpStatus;

import com.example.DemoGraphQL.model.FixedOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = Include.NON_NULL)
@NoArgsConstructor
public class FixedOrderResponse extends BaseResponse {

	private Iterable<FixedOrder> orders;

	private FixedOrder order;
	
	private Long count;

	public FixedOrderResponse(FixedOrder order) {
		super(HttpStatus.OK, "Everything looks good ");
		this.order = order;
	}
	
	public FixedOrderResponse(Iterable<FixedOrder> orders) {
		super(HttpStatus.OK, "Everything looks good ");
		this.orders = orders;
	}
	
	public FixedOrderResponse(Long count) {
		super(HttpStatus.OK, "Everything looks good ");
		this.count = count;
	}
}