package com.order.app;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.app.entities.Account;
import com.order.app.request.createAccountDTO;

@FeignClient(value = "account-api/account-app/account/", decode404 = true)
public interface IAccountService {

	@GetMapping("/status/check")
	public String status(); 

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<Account>> getAccountsByPage(@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size); 

	@GetMapping("/get/{AccountId}")
	public ResponseEntity<Account> getAccount(@PathVariable("AccountId") Long accountId); 

	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody createAccountDTO accountDTO); 
}
