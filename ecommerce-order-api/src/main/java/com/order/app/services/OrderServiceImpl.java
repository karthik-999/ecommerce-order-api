package com.order.app.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.order.app.IAccountService;
import com.order.app.IAddressService;
import com.order.app.ICartService;
import com.order.app.IPaymentInterface;
import com.order.app.IUserService;
import com.order.app.entities.Account;
import com.order.app.entities.Address;
import com.order.app.entities.Cart;
import com.order.app.entities.CartItem;
import com.order.app.entities.Order;
import com.order.app.entities.UserEntity;
import com.order.app.repositories.OrderRepository;
import com.order.app.request.PaymentDetailsRequest;
import com.order.app.requests.CreateOrderDetailsDTO;
import com.order.app.response.PaymentDeductionResponseDTO;
import com.order.app.service.interfaces.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	IAccountService accountService;

	@Autowired
	IAddressService addressService;

	@Autowired
	IUserService userService;

	@Autowired
	ICartService cartService;
	
	@Autowired
	IPaymentInterface paymentService;
	
	
	@Override
	public List<Order> findAll(Pageable pageable) {

		return orderRepository.findAll(pageable).getContent();

	}

	@Override
	public Order getOrder(Long orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if (optionalOrder.isPresent()) {
			return optionalOrder.get();
		}
		return new Order();

	}

	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order saveOrder1(CreateOrderDetailsDTO orderDTO) {
		if (!(orderDTO.getAccount() != null && orderDTO.getBillingAddress() != null
				&& orderDTO.getShippingAddress() != null && orderDTO.getUser() != null)) {
			throw new RuntimeException("order details Request check");
		}

		Account account = accountService.getAccount(orderDTO.getAccount()).getBody();
		Address billingAddress = addressService.getAddress(orderDTO.getBillingAddress()).getBody();
		Address shippingAddress = addressService.getAddress(orderDTO.getShippingAddress()).getBody();
		UserEntity user = userService.getUser1(orderDTO.getUser()).getBody();
		Cart cart = cartService.getCartByUserId(orderDTO.getUser()).getBody();
		List<CartItem> cartItems = new ArrayList();
		cartItems.addAll(cart.getCartItems());
		Order order = new Order();
		order.setAccount(account);
		order.setBillingAddress(billingAddress);
		order.setShippingAddress(shippingAddress);
		order.setUser(user);
		Double cartTotal = 0.0;
		for (CartItem cartItem : cartItems) {
			cartTotal  = cartTotal + cartItem.getPrice();
		}
		order.setOrderTotal(cartTotal);
		order.setOrderTrackingNumber(UUID.randomUUID().toString().replace("-", ""));
		order.setOrderNumber(UUID.randomUUID().toString().replace("-",""));
		order.setOrderDate(Date.valueOf(LocalDate.now()));
		BeanUtils.copyProperties(orderDTO, order, "account", "shippingAddress", "billingAddress", "user","orderTotal","orderTrackingNumber","orderNumber","orderDate");

		PaymentDetailsRequest paymentDetailsRequest = new PaymentDetailsRequest();
		paymentDetailsRequest.setAccountNumber(account.getAccountNumber());
		paymentDetailsRequest.setPrice(order.getOrderTotal());
		PaymentDeductionResponseDTO paymentDetails = paymentService.paymentDeduction(paymentDetailsRequest).getBody();
		if(!(paymentDetails != null && paymentDetails.getStatus()!= null && paymentDetails.getStatus().equalsIgnoreCase("success"))) {
			throw new RuntimeException("Payment Not Succesful - Check Balance");
		}
		
		return orderRepository.save(order);
	}
}
