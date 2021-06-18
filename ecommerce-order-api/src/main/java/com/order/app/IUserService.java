package com.order.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.app.entities.UserEntity;
import com.order.app.request.CreateUserRequestModel;
import com.order.app.request.CreateUserResponseModel;
import com.order.app.request.UserResponseModel;

@FeignClient(value = "user-api/user-app/users/", decode404 = true)
public interface IUserService {

	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails);

	@GetMapping(value="/{userId}")
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId);

	@GetMapping(value="/user/{userId}")
    public ResponseEntity<UserEntity> getUser1(@PathVariable("userId") Long userId);
}
