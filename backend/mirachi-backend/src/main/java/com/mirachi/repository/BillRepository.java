package com.mirachi.repository;

import java.math.BigDecimal;
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
    
    List<Bill> findByCustomerId(
            Long customerId);
    
    @Query("""
	       SELECT COALESCE(SUM(g.totalAmount),0)
	       FROM GrindingTransaction g
	       """)
	BigDecimal getTotalRevenue();
    
    @Query("""
	       SELECT COALESCE(SUM(b.totalAmount),0)
	       FROM Bill b
	       WHERE YEAR(b.billDate)=:year
	       AND MONTH(b.billDate)=:month
	       """)
	BigDecimal getMonthlyRevenue(
	        @Param("year") int year,
	        @Param("month") int month);
 
    @Query("""
	       SELECT COALESCE(SUM(b.totalAmount),0)
	       FROM Bill b
	       WHERE b.billDate BETWEEN :startDate AND :endDate
	       """)
	BigDecimal getRevenueBetweenDates(
	        @Param("startDate") LocalDate startDate,
	        @Param("endDate") LocalDate endDate);
    

}