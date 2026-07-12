package com.mirachi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExpenseRequestDto {
	
	@NotBlank(message = "Expense type is required")
	private String expenseType;
	
	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be greater than zero")
	private BigDecimal amount;
	
	private String description;
	
	private LocalDate expenseDate;
}
