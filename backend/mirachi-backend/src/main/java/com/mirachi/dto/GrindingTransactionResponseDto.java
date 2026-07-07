package com.mirachi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrindingTransactionResponseDto {
	
	private Long id;

    private String customerName;

    private String itemName;

    private Double weightInKg;

    private BigDecimal ratePerKg;

    private BigDecimal totalAmount;

    private LocalDate transactionDate;

    private String status;

    private String remarks;
}
