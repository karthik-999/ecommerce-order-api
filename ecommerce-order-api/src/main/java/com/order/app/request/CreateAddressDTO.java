package com.order.app.request;

import lombok.Data;

@Data
public class CreateAddressDTO {

	String houseNumber;

	String streetAddress;

	String city;

	String state;

	String zipCode;

	Long account;
}
