package com.mirachi.mapper;

import org.springframework.stereotype.Component;

import com.mirachi.dto.ExpenseResponseDto;
import com.mirachi.entity.Expense;

@Component
public class ExpenseMapper {

	public ExpenseResponseDto mapToResponse(Expense expense) {
		return ExpenseResponseDto.builder().id(expense.getId()).expenseType(expense.getExpeneseType())
				.amount(expense.getAmount()).description(expense.getDescription()).expenseDate(expense.getExpenseDate())
				.build();
	}
}
