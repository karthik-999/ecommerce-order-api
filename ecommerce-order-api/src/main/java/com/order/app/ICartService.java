package com.order.app;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.app.entities.Cart;

@FeignClient(value = "cart-api/cart-app/cart/", decode404 = true)
public interface ICartService {

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<Cart>> getCartsByPage(@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size);

	@GetMapping("/get/{CartId}")
	public ResponseEntity<Cart> getCart(@PathVariable("CartId") Long CartId);

	@GetMapping("/getcart/{userId}")
	public ResponseEntity<Cart> getCartByUserId(@PathVariable("userId") Long userId);
	
	@PostMapping("/save")
	public ResponseEntity<Cart> saveCart(@RequestBody Cart user);

}
