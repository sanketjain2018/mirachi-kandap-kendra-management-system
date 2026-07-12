package com.mirachi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mirachi.dto.ExpenseRequestDto;
import com.mirachi.dto.ExpenseResponseDto;
import com.mirachi.entity.Expense;
import com.mirachi.exception.ExpenseNotFoundException;
import com.mirachi.mapper.ExpenseMapper;
import com.mirachi.repository.ExpenseRepository;
import com.mirachi.service.ExpenseService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{
    
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    
    @Override
    public ExpenseResponseDto 
    	createExpense(ExpenseRequestDto request) {
	
	Expense expense = 
		Expense.builder()
			.expeneseType(
				request.getExpenseType())
			.amount(
				request.getAmount())
			.description(
				request.getDescription())
			.expenseDate(
				request.getExpenseDate())
			.build();
			
	Expense savedExpense =
	            expenseRepository.save(
	                    expense);
		
	
	return expenseMapper.mapToResponse(savedExpense);
    }
    
    @Override
    public ExpenseResponseDto getExpenseById(Long expenseId) {
	
	Expense expense = 
		expenseRepository.findById(expenseId)
		.orElseThrow(
			()-> 
				new ExpenseNotFoundException("Expense not found"));
		
	return expenseMapper
		.mapToResponse(expense);
    }
    @Override
    public List<ExpenseResponseDto> getAllExpenses() {
	
	return expenseRepository.findAll()
		.stream()
		.map(
			expenseMapper::mapToResponse)
		.toList();
    }

    @Override
    public ExpenseResponseDto
    updateExpense(
            Long expenseId,
            ExpenseRequestDto request) {

        Expense expense =
                expenseRepository.findById(
                        expenseId)
                        .orElseThrow(
                                () ->
                                        new ExpenseNotFoundException(
                                                "Expense not found"));

        expense.setExpeneseType(
                request.getExpenseType());

        expense.setAmount(
                request.getAmount());

        expense.setDescription(
                request.getDescription());

        expense.setExpenseDate(
                request.getExpenseDate());

        Expense updatedExpense =
                expenseRepository.save(
                        expense);

        return expenseMapper
                .mapToResponse(
                        updatedExpense);
    }

    @Override
    public void deleteExpense(
            Long expenseId) {

        Expense expense =
                expenseRepository.findById(
                        expenseId)
                        .orElseThrow(
                                () ->
                                        new ExpenseNotFoundException(
                                                "Expense not found"));

        expenseRepository.delete(
                expense);
    }
}
