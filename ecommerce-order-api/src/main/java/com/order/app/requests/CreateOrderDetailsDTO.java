package com.order.app.requests;

import java.util.Date;

import lombok.Data;

@Data
public class CreateOrderDetailsDTO {

	private String orderNumber;
	private String orderTrackingNumber;
	private Date orderDate;
	private Double orderTotal;
	private Long user;
	private Long shippingAddress;
	private Long billingAddress;
	private Long account;
//	private Set<OrderLineItem> orderItems = new HashSet<OrderLineItem>();
}
