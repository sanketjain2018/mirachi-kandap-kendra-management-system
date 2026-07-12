package com.mirachi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseResponseDto {
	
	private Long id;
	private String expenseType;
	private BigDecimal amount;
	private String description;
	private LocalDate expenseDate;
}
