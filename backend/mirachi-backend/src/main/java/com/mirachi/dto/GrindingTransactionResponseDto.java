package com.mirachi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrindingTransactionResponseDto {
	
	private Long id;
	private String customerName;
	private String itemName;
	private Double weightInKg;
	private BigDecimal ratePerKg;
	private BigDecimal toalAmount;
	private LocalDate transactionDate;
	private String status;
	private String remarks;
}
