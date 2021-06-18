package com.order.app.entities;

import lombok.Data;

@Data
public class AddressDTO {

	
	private long id;
	
	private String houseNumber;
	
	private String streetAddress;
	
	private String city;
	
	private String state;
	
	private String zipCode;

	private long accountId;
	
}
