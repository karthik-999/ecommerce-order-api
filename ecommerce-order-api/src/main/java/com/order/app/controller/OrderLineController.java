package com.order.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.app.entities.OrderLineItem;
import com.order.app.service.interfaces.IOrderLineItemService;

@RestController
@RequestMapping("/orderLine/")
public class OrderLineController {

	@Autowired
	IOrderLineItemService orderLineItemService;

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<OrderLineItem>> getOrderLinesByPage(@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		List<OrderLineItem> orderLines = orderLineItemService.findAll(pageable);
		return new ResponseEntity<>(orderLines, HttpStatus.OK);
	}

	@GetMapping("/get/{OrderLineId}")
	public ResponseEntity<OrderLineItem> getOrderLine(@PathVariable("OrderLineId") Long OrderLineId) {
		return new ResponseEntity<>(orderLineItemService.getOrderLineItem(OrderLineId), HttpStatus.OK);
	}	

	@PostMapping("/save")
	public ResponseEntity<OrderLineItem> saveOrderLine(@RequestBody OrderLineItem orderLine) {
		return new ResponseEntity<>(orderLineItemService.saveOrderLineItem(orderLine), HttpStatus.CREATED);
	}
}