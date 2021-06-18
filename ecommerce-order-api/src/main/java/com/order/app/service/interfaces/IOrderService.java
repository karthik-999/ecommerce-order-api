package com.order.app.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.order.app.entities.Order;
import com.order.app.requests.CreateOrderDetailsDTO;

public interface IOrderService {

	public List<Order> findAll(Pageable pageable);

	public Order getOrder(Long orderId);

	public Order saveOrder(Order order);

	public Order saveOrder1(CreateOrderDetailsDTO order);

}
