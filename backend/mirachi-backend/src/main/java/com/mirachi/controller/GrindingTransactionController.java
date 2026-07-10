
package com.mirachi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.DashboardSummaryDto;
import com.mirachi.dto.GrindingTransactionRequestDto;
import com.mirachi.dto.GrindingTransactionResponseDto;
import com.mirachi.dto.RevenueResponseDto;
import com.mirachi.service.GrindingTransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class GrindingTransactionController {

	private final GrindingTransactionService transactionService;

	@PostMapping
	public ApiResponse<GrindingTransactionResponseDto> createTransaction(
			@Valid @RequestBody GrindingTransactionRequestDto request) {

		return transactionService.createTransaction(request);
	}

	@GetMapping
	public ApiResponse<List<GrindingTransactionResponseDto>> getAllTransactions() {

		return transactionService.getAllTransactions();
	}

	@GetMapping("/{id}")
	public ApiResponse<GrindingTransactionResponseDto> getTransactionById(@PathVariable Long id) {

		return transactionService.getTransactionById(id);
	}

	@PutMapping("/{id}")
	public ApiResponse<GrindingTransactionResponseDto> updateTransaction(@PathVariable Long id,
			@Valid @RequestBody GrindingTransactionRequestDto request) {

		return transactionService.updateTransaction(id, request);
	}

	@PatchMapping("/{id}/cancel")
	public ApiResponse<String> cancelTransaction(@PathVariable Long id) {

		return transactionService.cancelTransaction(id);
	}

	@GetMapping("/customer/{customerId}")
	public ApiResponse<List<GrindingTransactionResponseDto>> getTransactionsByCustomer(@PathVariable Long customerId) {

		return transactionService.getTransactionsByCustomer(customerId);
	}

	@GetMapping("/rate/{rateMasterId}")
	public ApiResponse<List<GrindingTransactionResponseDto>> getTransactionsByRate(@PathVariable Long rateMasterId) {

		return transactionService.getTransactionsByRate(rateMasterId);
	}

	@GetMapping("/date")
	public ApiResponse<List<GrindingTransactionResponseDto>> getTransactionsByDate(

			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

		return transactionService.getTransactionsByDate(date);
	}

	// Pagination

	@GetMapping("/page")
	public ApiResponse<Page<GrindingTransactionResponseDto>> getTransactionsPage(
			@RequestParam(defaultValue = "0") int page,

			@RequestParam(defaultValue = "10") int size,

			@RequestParam(defaultValue = "createdAt") String sortBy) {

		return new ApiResponse<>(true, "Transactions fetched successfully",
				transactionService.getTransactionsWithPagination(page, size, sortBy));
	}

	// Daily Revenue

	@GetMapping("/revenue/daily")
	public ApiResponse<RevenueResponseDto> getDailyRevenue() {

		return new ApiResponse<>(true, "Daily revenue fetched successfully", transactionService.getDailyRevenue());
	}

	// Monthly Revenue

	@GetMapping("/revenue/monthly")
	public ApiResponse<RevenueResponseDto> getMonthlyRevenue(@RequestParam int year, @RequestParam int month) {

		return new ApiResponse<>(true, "Monthly revenue fetched successfully",
				transactionService.getMonthlyRevenue(year, month));
	}

	// Yearly Revenue

	@GetMapping("/revenue/yearly")
	public ApiResponse<RevenueResponseDto> getYearlyRevenue(@RequestParam int year) {

		return new ApiResponse<>(true, "Yearly revenue fetched successfully",
				transactionService.getYearlyRevenue(year));
	}

	// Date Range Revenue

	@GetMapping("/revenue/range")
	public ApiResponse<RevenueResponseDto> getRevenueBetweenDates(@RequestParam LocalDate fromDate,
			@RequestParam LocalDate toDate) {

		return new ApiResponse<>(true, "Revenue report fetched successfully",
				transactionService.getRevenueBetweenDates(fromDate, toDate));
	}
	
	
	// Dashboard Info
	@GetMapping("/dashboard/summary")
	public ApiResponse<DashboardSummaryDto>
	getDashboardSummary() {

	    return new ApiResponse<>(
	            true,
	            "Dashboard summary fetched successfully",
	            transactionService
	                    .getDashboardSummary());
	}
	

}