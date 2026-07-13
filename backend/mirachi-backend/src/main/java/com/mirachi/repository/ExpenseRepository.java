package com.mirachi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirachi.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
    List<Expense> findByExpenseTypeContainingIgnoreCase(
	        String expenseType);
    
    List<Expense> findByExpenseDateBetween(
	        LocalDate startDate,
	        LocalDate endDate);
    
}
