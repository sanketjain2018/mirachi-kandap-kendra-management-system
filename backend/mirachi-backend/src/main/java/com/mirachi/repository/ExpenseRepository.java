package com.mirachi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mirachi.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByExpenseTypeContainingIgnoreCase(String expenseType);

    List<Expense> findByExpenseDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("""
    	SELECT COALESCE(SUM(e.amount),0)
    	FROM Expense e
    	""")
    BigDecimal getTotalExpenses();

    @Query("""
    	SELECT COALESCE(SUM(e.amount),0)
    	FROM Expense e
    	WHERE YEAR(e.expenseDate)=:year
    	AND MONTH(e.expenseDate)=:month
    	""")
    BigDecimal getMonthlyExpense(@Param("year") int year, @Param("month") int month);

    @Query("""
    	SELECT COALESCE(SUM(e.amount),0)
    	FROM Expense e
    	WHERE e.expenseDate BETWEEN :startDate AND :endDate
    	""")
    BigDecimal getExpenseBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("""
    	SELECT e.expenseType,
    	       SUM(e.amount)
    	FROM Expense e
    	GROUP BY e.expenseType
    	ORDER BY SUM(e.amount) DESC
    	""")
    List<Object[]> getTopExpenseCategories();

}
