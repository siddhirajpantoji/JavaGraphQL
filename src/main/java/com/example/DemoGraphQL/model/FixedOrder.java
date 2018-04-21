package com.example.DemoGraphQL.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@Entity
public class FixedOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String baseCurrency;

	private String quoteCurrency;

	private Float baseAmount;

	private Float quoteAmount;

	private String senderId;

	private String beneficiaryId;

	private String purpose;

	private Date createdAt;

	private Date updatedAt;

	private Float rate;

	private String status;
	
	@OneToMany(fetch= FetchType.EAGER, mappedBy= "order")
	private List<OrderStatus> history;
	
	@PrePersist
	void onCreate()
	{
		this.createdAt = new Date();
		
	}
	@PreUpdate
	void onUpdate()
	{
		
		this.updatedAt  = new Date();
	}
}
