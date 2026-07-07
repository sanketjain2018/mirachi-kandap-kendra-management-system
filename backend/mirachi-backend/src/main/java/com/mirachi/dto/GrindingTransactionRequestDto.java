package com.mirachi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GrindingTransactionRequestDto {
	
	@NotNull
	private Long customerId;
	
	@NotNull
	private Long rateMasterId;
	
	@NotNull
	@Positive
	private Double weightInKg;
	private String remarks;
	
}
