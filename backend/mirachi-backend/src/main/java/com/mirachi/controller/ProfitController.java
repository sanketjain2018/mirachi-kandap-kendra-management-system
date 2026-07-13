package com.mirachi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.MonthlyProfitDto;
import com.mirachi.dto.ProfitDateRangeDto;
import com.mirachi.dto.ProfitSummaryDto;
import com.mirachi.dto.RevenueExpenseDto;
import com.mirachi.dto.TopExpenseDto;
import com.mirachi.service.ProfitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profit/")
@RequiredArgsConstructor
public class ProfitController {
    
    private final ProfitService profitService;
    
    @GetMapping("/summary")
    public ApiResponse<ProfitSummaryDto> getProfitSummary(){
	return new ApiResponse<>(
		true,
                "Profit summary fetched successfully",
                profitService.getProfitSummary());
    }
    
    @GetMapping("/monthly")
    public ApiResponse<MonthlyProfitDto>
    getCurrentMonthProfit() {

        return new ApiResponse<>(
                true,
                "Monthly profit fetched successfully",
                profitService
                        .getCurrentMonthProfit()
        );
    }
    
    @GetMapping("/date-range")
    public ApiResponse<ProfitDateRangeDto>
    getProfitBetweenDates(

            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return new ApiResponse<>(
                true,
                "Profit report fetched successfully",
                profitService.getProfitBetweenDates(
                        startDate,
                        endDate));
    }
    
    @GetMapping("/revenue-vs-expense")
    public ApiResponse<RevenueExpenseDto>
    getRevenueVsExpense() {

        return new ApiResponse<>(
                true,
                "Revenue vs Expense fetched successfully",
                profitService.getRevenueVsExpense());
    }
    
    @GetMapping("/top-expenses")
    public ApiResponse<List<TopExpenseDto>>
    getTopExpenseCategories() {

        return new ApiResponse<>(
                true,
                "Top expense categories fetched",
                profitService
                        .getTopExpenseCategories());
    }
    
}

