package com.example.DemoGraphQL.services;

import com.example.DemoGraphQL.model.FixedOrder;

public interface OrderService {

	/**
	 * Search Query of Orders
	 * 
	 * @param id
	 * @param status
	 * @param senderId
	 * @param beneficiaryId
	 * @return
	 */
	public Iterable<FixedOrder> getOrdersBy(Long id, String status, String senderId, String beneficiaryId);

	/**
	 * Get Count of All Orders
	 * 
	 * @param id
	 * @param status
	 * @param senderId
	 * @param beneficiaryId
	 * @return
	 */
	public long getOrderCount(Long id, String status, String senderId, String beneficiaryId);

	/**
	 * 
	 * @param baseCurrency
	 * @param quoteCurrency
	 * @param baseAmount
	 * @param senderId
	 * @param beneficiaryId
	 * @param purpose
	 * @return
	 */
	public FixedOrder getNewOrder(String baseCurrency, String quoteCurrency, Float baseAmount, String senderId,
			String beneficiaryId, String purpose);

	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public FixedOrder updateOrderStatus(Long id, String status);
}