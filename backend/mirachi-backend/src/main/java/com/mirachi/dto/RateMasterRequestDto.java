package com.mirachi.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RateMasterRequestDto {

	@NotBlank(message = "Item name is required")
	private String itemName;
	
	@NotNull(message = "Rate per KG is required")
	@Positive(message = "Rate per KG must be greater than zero")
	private BigDecimal ratePerKg;
	private String description;
}
