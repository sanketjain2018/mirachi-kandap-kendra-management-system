package com.mirachi.repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mirachi.entity.GrindingTransaction;

public interface GrindingTransactionRepository extends 
						JpaRepository<GrindingTransaction, Long> {
	
	List<GrindingTransaction> 
			findByCustomerId(Long customerId);
	
	List<GrindingTransaction> 
			findByRateMasterId(Long rateMasterId);
	
	List<GrindingTransaction> 
			findByTransactionDate(LocalDate date);
	
	// Pagination
	Page<GrindingTransaction> finaAll(Pageable pageable);
	
	// Daily Revenue
	@Query("""
			SELECT COALESCE(SUM(gt.totalAmount),0)
           FROM GrindingTransaction gt
           WHERE gt.transactionDate = :date
           AND gt.status='COMPLETED'
			""")
	BigDecimal getDailyRevenue(LocalDate date);
	
	
	// Monthly Revenue
	@Query("""
			SELECT COALESCE(SUM(gt.totalAmount),0)
           FROM GrindingTransaction gt
           WHERE YEAR(gt.transactionDate)=:year
           AND MONTH(gt.transactionDate)=:month
           AND gt.status='COMPLETED'
			""")
	BigDecimal getMonthlyRevenue(int year,
								int month);
	
	
	// Yearly Revenue
	@Query(""" 
			SELECT COALESCE(SUM(gt.totalAmount),0)
			FROM GrindingTransaction gt
			WHERE YEAR(gt.transactionDate)=:year
			AND gt.status='COMPLETED'
			""")
	BigDecimal getYearlyRevenue(int year);
	
	// Date Range Revenue
	BigDecimal getRevenueBetweenDates(LocalDate fromDate,
										LocalDate toDate);
	
}






