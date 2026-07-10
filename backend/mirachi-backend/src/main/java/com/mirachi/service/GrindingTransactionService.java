package com.mirachi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.GrindingTransactionRequestDto;
import com.mirachi.dto.GrindingTransactionResponseDto;
import com.mirachi.dto.RevenueResponseDto;

public interface GrindingTransactionService {

	// Create Transaction
    ApiResponse<GrindingTransactionResponseDto>
    createTransaction(
            GrindingTransactionRequestDto request);

    // Get All Transactions
    ApiResponse<List<GrindingTransactionResponseDto>>
    getAllTransactions();

    // Get Transaction By Id
    ApiResponse<GrindingTransactionResponseDto>
    getTransactionById(Long id);

    // Update Transaction
    ApiResponse<GrindingTransactionResponseDto>
    updateTransaction(
            Long id,
            GrindingTransactionRequestDto request);

    // Cancel Transaction
    ApiResponse<String>
    cancelTransaction(Long id);
    
    // Get transaction By Customer
    ApiResponse<List<GrindingTransactionResponseDto>>
    getTransactionsByCustomer(Long customerId);

    // Get Transaction By Rate 
    ApiResponse<List<GrindingTransactionResponseDto>>
    getTransactionsByRate(Long rateMasterId);
    
    // Get Transaction By Date
    ApiResponse<List<GrindingTransactionResponseDto>>
    getTransactionsByDate(LocalDate date);
    
    // Get Transactions in Page format
    Page<GrindingTransactionResponseDto> getTransactionsWithPagination(
            int page,
            int size,
            String sortBy);
    
    // Get Daily Revenue
    RevenueResponseDto getDailyRevenue();
    
    // Get Monthly Revenue
    RevenueResponseDto getMonthlyRevenue(
    		int year,
    		int month);
    
    // Get Yearly Revenue
    RevenueResponseDto getYearlyRevenue(
    		int year);
    
    // Get Revenue Between Dates Customer Range
    RevenueResponseDto getRevenueBetweenDates(
    		LocalDate fromDate,
    		LocalDate toDate);
    
    
    
    
    
    
    
    
    
    
}