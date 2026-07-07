package com.mirachi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.GrindingTransaction;

public interface GrindingTransactionRepository extends 
						JpaRepository<GrindingTransaction, Long> {
	
	List<GrindingTransaction> 
			findByCustomerId(Long customerId);
	
	List<GrindingTransaction> 
			findByRateMasterId(Long rateMasterId);
	
	List<GrindingTransaction> 
			findByTransactionDate(LocalDate date);
}
