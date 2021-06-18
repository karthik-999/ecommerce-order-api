package com.order.app;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.app.entities.Address;
import com.order.app.request.CreateAddressDTO;

@FeignClient(value = "address-api/address-app/address/", decode404 = true)
public interface IAddressService {
	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<Address>> getAddresssByPage(@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size);

	@GetMapping("/get/{AddressId}")
	public ResponseEntity<Address> getAddress(@PathVariable("AddressId") Long AddressId);

	@PostMapping("/save")
	public ResponseEntity<Address> saveAddress(@RequestBody CreateAddressDTO address);
}
