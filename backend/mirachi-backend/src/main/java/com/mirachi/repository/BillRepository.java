package com.mirachi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mirachi.entity.Bill;
import com.mirachi.enums.PaymentStatus;

public interface BillRepository
        extends JpaRepository<Bill, Long> {

    Optional<Bill> findByBillNumber(
            String billNumber);
    
    List<Bill> findByPaymentStatus(
            PaymentStatus paymentStatus);
    
    List<Bill> findByBillDateBetween(
            LocalDate startDate,
            LocalDate endDate);
    
    @Query("""
    		SELECT b
    		FROM Bill b
    		WHERE LOWER(
    		    b.customer.customerName
    		)
    		LIKE LOWER(
    		    CONCAT('%', :customerName, '%')
    		)
    		""")
    		List<Bill> searchByCustomerName(
    		        @Param("customerName")
    		        String customerName);
}