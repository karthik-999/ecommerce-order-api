package com.order.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.order.app.entities.OrderLineItem;
import com.order.app.repositories.OrderLineItemRepository;
import com.order.app.service.interfaces.IOrderLineItemService;

@Service
public class OrderLineItemServiceImpl implements IOrderLineItemService {

	@Autowired
	OrderLineItemRepository orderLineItemRepository;

	@Override
	public List<OrderLineItem> findAll(Pageable pageable) {

		return orderLineItemRepository.findAll(pageable).getContent();

	}

	@Override
	public OrderLineItem getOrderLineItem(Long orderLineItemId) {
		Optional<OrderLineItem> optionalOrderLineItem = orderLineItemRepository.findById(orderLineItemId);
		if (optionalOrderLineItem.isPresent()) {
			return optionalOrderLineItem.get();
		}
		return new OrderLineItem();

	}

	@Override
	public OrderLineItem saveOrderLineItem(OrderLineItem orderLineItem) {
		return orderLineItemRepository.save(orderLineItem);
	}
}
