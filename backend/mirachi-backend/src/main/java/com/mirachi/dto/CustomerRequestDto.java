package com.mirachi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerRequestDto {
	
	@NotBlank(message = "Customer Name is required")
	private String customerName;
	@Pattern(regexp = "^[0-9]{10}$",
			message = "Mobile Number must be 10 digits")
	private String mobileNumber;
	private String address;
}
