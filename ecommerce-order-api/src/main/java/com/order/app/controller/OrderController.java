package com.order.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.order.app.IPaymentInterface;
import com.order.app.entities.Order;
import com.order.app.request.PaymentDetailsRequest;
import com.order.app.requests.CreateOrderDetailsDTO;
import com.order.app.response.PaymentDeductionResponseDTO;
import com.order.app.service.interfaces.IOrderService;

@RestController
@RequestMapping("/order/")
public class OrderController {

	@Autowired
	IOrderService orderService;
	
	@Autowired
	IPaymentInterface paymentService;
	
	@Autowired
	Environment env;
	
	@GetMapping("/status/check")
	public String status()
	{
		return "Working on port " + env.getProperty("local.server.port");
	}

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<Order>> getOrdersByPage(@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		List<Order> orders = orderService.findAll(pageable);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/get/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable("orderId") Long orderId) {
		return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
	}

//	@PostMapping("/createOrder")
//	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//		return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
//	}

	@PostMapping("/createOrder")
	public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDetailsDTO order) {
		return new ResponseEntity<>(orderService.saveOrder1(order), HttpStatus.CREATED);
	}
	
	@PostMapping("/payOrder")
	public ResponseEntity<PaymentDeductionResponseDTO> payOrder(@RequestBody PaymentDetailsRequest paymentDetailsRequest) {
		return paymentService.paymentDeduction(paymentDetailsRequest);
	
	}
}