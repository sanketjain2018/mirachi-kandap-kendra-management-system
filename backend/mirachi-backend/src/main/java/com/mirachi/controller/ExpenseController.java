package com.mirachi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.ExpenseRequestDto;
import com.mirachi.dto.ExpenseResponseDto;
import com.mirachi.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    
    private final ExpenseService expenseService;
    
    
    @PostMapping
    public ApiResponse<ExpenseResponseDto>
    createExpense(
            @Valid
            @RequestBody
            ExpenseRequestDto request) {

        return new ApiResponse<>(
                true,
                "Expense created successfully",
                expenseService
                        .createExpense(
                                request));
    }
    
    @GetMapping("/{id}")
    public ApiResponse<ExpenseResponseDto>
    	getExpenseById(
    		@PathVariable Long id){
	return new ApiResponse<>(
		true,
		"Expense fetched successfully",
		expenseService
			.getExpenseById(id));
    }
    
    
    @GetMapping
    public ApiResponse<List<ExpenseResponseDto>>
    getAllExpenses() {

        return new ApiResponse<>(
                true,
                "Expenses fetched successfully",
                expenseService
                        .getAllExpenses());
    }
    
    @PutMapping("/{id}")
    public ApiResponse<ExpenseResponseDto>
    updateExpense(
            @PathVariable Long id,

            @Valid
            @RequestBody
            ExpenseRequestDto request) {

        return new ApiResponse<>(
                true,
                "Expense updated successfully",
                expenseService
                        .updateExpense(
                                id,
                                request));
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse<String>
    deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(
                id);

        return new ApiResponse<>(
                true,
                "Expense deleted successfully",
                "Deleted");
    }
    
    @GetMapping("/type/{expenseType}")
    public ApiResponse<List<ExpenseResponseDto>>
    searchByExpenseType(
            @PathVariable String expenseType) {

        return new ApiResponse<>(
                true,
                "Expenses fetched successfully",
                expenseService
                        .searchByExpenseType(
                                expenseType));
    }
    
    @GetMapping("/date-range")
    public ApiResponse<List<ExpenseResponseDto>>
    searchByDateRange(

            @RequestParam
            LocalDate startDate,

            @RequestParam
            LocalDate endDate) {

        return new ApiResponse<>(
                true,
                "Expenses fetched successfully",
                expenseService
                        .searchByDateRange(
                                startDate,
                                endDate));
    }
    
    
    
    
    
    
}
