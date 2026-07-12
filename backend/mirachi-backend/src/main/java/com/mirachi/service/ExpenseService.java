package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.ExpenseRequestDto;
import com.mirachi.dto.ExpenseResponseDto;

public interface ExpenseService {
    
    
    ExpenseResponseDto createExpense(
	    ExpenseRequestDto request );
  
    ExpenseResponseDto getExpenseById(
	    Long expenseId);
    
    List<ExpenseResponseDto> getAllExpenses();
    
    ExpenseResponseDto updateExpense(
	    Long id, ExpenseRequestDto request);
    
    void deleteExpense(Long id);
}
