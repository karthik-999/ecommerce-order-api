package com.order.app.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.order.app.entities.OrderLineItem;

public interface IOrderLineItemService {

	public List<OrderLineItem> findAll(Pageable pageable);

	public OrderLineItem getOrderLineItem(Long OrderLineItemId);

	public OrderLineItem saveOrderLineItem(OrderLineItem OrderLineItem);

}
