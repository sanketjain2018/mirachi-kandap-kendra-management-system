package com.mirachi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.DashboardSummaryDto;
import com.mirachi.dto.GrindingTransactionRequestDto;
import com.mirachi.dto.GrindingTransactionResponseDto;
import com.mirachi.dto.RevenueResponseDto;
import com.mirachi.entity.Customer;
import com.mirachi.entity.GrindingTransaction;
import com.mirachi.entity.RateMaster;
import com.mirachi.enums.TransactionStatus;
import com.mirachi.exception.CustomerNotFoundException;
import com.mirachi.exception.RateMasterNotFoundException;
import com.mirachi.exception.TransactionNotFoundException;
import com.mirachi.repository.CustomerRepository;
import com.mirachi.repository.GrindingTransactionRepository;
import com.mirachi.repository.RateMasterRepository;
import com.mirachi.service.GrindingTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GrindingTransactionServiceImpl
        implements GrindingTransactionService {

    private final GrindingTransactionRepository
            transactionRepository;

    private final CustomerRepository
            customerRepository;

    private final RateMasterRepository
            rateMasterRepository;

    @Override
    public ApiResponse<GrindingTransactionResponseDto>
    createTransaction(
            GrindingTransactionRequestDto request) {

        Customer customer =
                customerRepository.findById(
                        request.getCustomerId())
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer not found"));

        RateMaster rateMaster =
                rateMasterRepository.findById(
                        request.getRateMasterId())
                        .orElseThrow(() ->
                                new RateMasterNotFoundException(
                                        "Rate not found"));

        BigDecimal ratePerKg =
                rateMaster.getRatePerKg();

        BigDecimal totalAmount =
                ratePerKg.multiply(
                        BigDecimal.valueOf(
                                request.getWeightInKg()));

        GrindingTransaction transaction =
                GrindingTransaction.builder()
                        .customer(customer)
                        .rateMaster(rateMaster)
                        .weightInKg(
                                request.getWeightInKg())
                        .ratePerKg(ratePerKg)
                        .totalAmount(totalAmount)
                        .remarks(
                                request.getRemarks())
                        .build();

        GrindingTransaction saved =
                transactionRepository.save(
                        transaction);

        return new ApiResponse<>(
                true,
                "Transaction created successfully",
                mapToResponse(saved));
    }

    @Override
    public ApiResponse<List<GrindingTransactionResponseDto>>
    getAllTransactions() {

        List<GrindingTransactionResponseDto>
                transactions =
                transactionRepository.findAll()
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        return new ApiResponse<>(
                true,
                "Transactions fetched successfully",
                transactions);
    }

    @Override
    public ApiResponse<GrindingTransactionResponseDto>
    getTransactionById(Long id) {

        GrindingTransaction transaction =
                transactionRepository.findById(id)
                        .orElseThrow(() ->
                                new TransactionNotFoundException(
                                        "Transaction not found"));

        return new ApiResponse<>(
                true,
                "Transaction fetched successfully",
                mapToResponse(transaction));
    }

    @Override
    public ApiResponse<GrindingTransactionResponseDto>
    updateTransaction(
            Long id,
            GrindingTransactionRequestDto request) {

        GrindingTransaction transaction =
                transactionRepository.findById(id)
                        .orElseThrow(() ->
                                new TransactionNotFoundException(
                                        "Transaction not found"));

        Customer customer =
                customerRepository.findById(
                        request.getCustomerId())
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer not found"));

        RateMaster rateMaster =
                rateMasterRepository.findById(
                        request.getRateMasterId())
                        .orElseThrow(() ->
                                new RateMasterNotFoundException(
                                        "Rate not found"));

        BigDecimal ratePerKg =
                rateMaster.getRatePerKg();

        BigDecimal totalAmount =
                ratePerKg.multiply(
                        BigDecimal.valueOf(
                                request.getWeightInKg()));

        transaction.setCustomer(customer);
        transaction.setRateMaster(rateMaster);
        transaction.setWeightInKg(
                request.getWeightInKg());
        transaction.setRatePerKg(ratePerKg);
        transaction.setTotalAmount(totalAmount);
        transaction.setRemarks(
                request.getRemarks());

        GrindingTransaction updated =
                transactionRepository.save(
                        transaction);

        return new ApiResponse<>(
                true,
                "Transaction updated successfully",
                mapToResponse(updated));
    }

    @Override
    public ApiResponse<String>
    cancelTransaction(Long id) {

        GrindingTransaction transaction =
                transactionRepository.findById(id)
                        .orElseThrow(() ->
                                new TransactionNotFoundException(
                                        "Transaction not found"));

        transaction.setStatus(
                TransactionStatus.CANCELLED);

        transactionRepository.save(transaction);

        return new ApiResponse<>(
                true,
                "Transaction cancelled successfully",
                null);
    }

    private GrindingTransactionResponseDto
    mapToResponse(
            GrindingTransaction transaction) {

        return GrindingTransactionResponseDto
                .builder()
                .id(transaction.getId())
                .customerName(
                        transaction.getCustomer()
                                .getCustomerName())
                .itemName(
                        transaction.getRateMaster()
                                .getItemName())
                .weightInKg(
                        transaction.getWeightInKg())
                .ratePerKg(
                        transaction.getRatePerKg())
                .totalAmount(
                        transaction.getTotalAmount())
                .transactionDate(
                        transaction.getTransactionDate())
                .status(
                        transaction.getStatus()
                                .name())
                .remarks(
                        transaction.getRemarks())
                .build();
    }
    
    
	/* search customer - searching functionality */
    
    @Override
    public ApiResponse<List<GrindingTransactionResponseDto>>
    getTransactionsByCustomer(Long customerId) {

        List<GrindingTransactionResponseDto> transactions =
                transactionRepository
                        .findByCustomerId(customerId)
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        return new ApiResponse<>(
                true,
                "Transactions fetched successfully",
                transactions);
    }
    
    
	/* rate search - searching functionality */
    
    @Override
    public ApiResponse<List<GrindingTransactionResponseDto>>
    getTransactionsByRate(Long rateMasterId) {

        List<GrindingTransactionResponseDto> transactions =
                transactionRepository
                        .findByRateMasterId(rateMasterId)
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        return new ApiResponse<>(
                true,
                "Transactions fetched successfully",
                transactions);
    }
    
    
	/* date search - searching functionality */
    
    @Override
    public ApiResponse<List<GrindingTransactionResponseDto>>
    getTransactionsByDate(LocalDate date) {

        List<GrindingTransactionResponseDto> transactions =
                transactionRepository
                        .findByTransactionDate(date)
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        return new ApiResponse<>(
                true,
                "Transactions fetched successfully",
                transactions);
    }
    
    
    // Get Transactions With Pagination
	@Override
	public Page getTransactionsWithPagination(int page, int size, String sortBy) {
		
		Pageable pageable =
	            PageRequest.of(
	                    page,
	                    size,
	                    Sort.by(sortBy).descending());

	    return transactionRepository
	            .findAll(pageable)
	            .map(this::mapToResponse);
		
	}

	@Override
	public RevenueResponseDto getDailyRevenue() {

	    BigDecimal revenue =
	            transactionRepository
	                    .getDailyRevenue(LocalDate.now());

	    return RevenueResponseDto.builder()
	            .reportType("Daily Revenue")
	            .revenue(revenue)
	            .build();
	}

	@Override
	public RevenueResponseDto
	getMonthlyRevenue(
	        int year,
	        int month) {

	    BigDecimal revenue =
	            transactionRepository
	                    .getMonthlyRevenue(year, month);

	    return RevenueResponseDto.builder()
	            .reportType("Monthly Revenue")
	            .revenue(revenue)
	            .build();
	}

	@Override
	public RevenueResponseDto
	getYearlyRevenue(
	        int year) {

	    BigDecimal revenue =
	            transactionRepository
	                    .getYearlyRevenue(year);

	    return RevenueResponseDto.builder()
	            .reportType("Yearly Revenue")
	            .revenue(revenue)
	            .build();
	}

	

	// Get Revenue Between Dates
    
	@Override
	public RevenueResponseDto
	getRevenueBetweenDates(
	        LocalDate fromDate,
	        LocalDate toDate) {

	    BigDecimal revenue =
	            transactionRepository
	                    .getRevenueBetweenDates(
	                            fromDate,
	                            toDate);

	    return RevenueResponseDto.builder()
	            .reportType("Date Range Revenue")
	            .revenue(revenue)
	            .build();
	}

	// Dashboard All Details
	
	@Override
	public DashboardSummaryDto getDashboardSummary() {
		
		 BigDecimal todayRevenue =
		            transactionRepository
		                    .getDailyRevenue(LocalDate.now());

		    BigDecimal monthRevenue =
		            transactionRepository
		                    .getMonthlyRevenue(
		                            LocalDate.now().getYear(),
		                            LocalDate.now().getMonthValue());

		    BigDecimal yearRevenue =
		            transactionRepository
		                    .getYearlyRevenue(
		                            LocalDate.now().getYear());

		    return DashboardSummaryDto.builder()
		            .todayRevenue(todayRevenue)
		            .monthRevenue(monthRevenue)
		            .yearRevenue(yearRevenue)
		            .totalCustomers(
		                    customerRepository.count())
		            .totalTransactions(
		                    transactionRepository.count())
		            .activeRates(
		                    rateMasterRepository
		                            .countByActiveTrue())
		            .build();
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}