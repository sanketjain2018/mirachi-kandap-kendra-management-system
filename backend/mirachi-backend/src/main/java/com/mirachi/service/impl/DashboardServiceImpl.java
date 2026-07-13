package com.mirachi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.mirachi.dto.DashboardSummaryDto;
import com.mirachi.dto.GrindingTransactionResponseDto;
import com.mirachi.dto.ProfitTrendDto;
import com.mirachi.dto.RevenueChartDto;
import com.mirachi.dto.RevenueExpenseDto;
import com.mirachi.dto.TopExpenseDto;
import com.mirachi.dto.TopItemDto;
import com.mirachi.mapper.GrindingTransactionMapper;
import com.mirachi.repository.BillRepository;
import com.mirachi.repository.CustomerRepository;
import com.mirachi.repository.ExpenseRepository;
import com.mirachi.repository.GrindingTransactionRepository;
import com.mirachi.repository.RateMasterRepository;
import com.mirachi.service.DashboardService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final GrindingTransactionRepository transactionRepository;

    private final CustomerRepository customerRepository;

    private final RateMasterRepository rateMasterRepository;
    
    private final GrindingTransactionMapper transactionMapper;
    
    private final BillRepository billRepository;

    private final ExpenseRepository expenseRepository;
    
 
    @Override
    public DashboardSummaryDto getDashboardSummary() {

        LocalDate today = LocalDate.now();

        BigDecimal totalRevenue =
                billRepository.getTotalRevenue();

        BigDecimal totalExpenses =
                expenseRepository.getTotalExpenses();

        BigDecimal totalProfit =
                totalRevenue.subtract(totalExpenses);

        return DashboardSummaryDto.builder()

                .todayRevenue(
                        transactionRepository
                                .getDailyRevenue(today))

                .monthRevenue(
                        transactionRepository
                                .getMonthlyRevenue(
                                        today.getYear(),
                                        today.getMonthValue()))

                .yearRevenue(
                        transactionRepository
                                .getYearlyRevenue(
                                        today.getYear()))

                .totalCustomers(
                        customerRepository.count())

                .totalTransactions(
                        transactionRepository.count())

                .activeRates(
                        rateMasterRepository
                                .countByActiveTrue())

                .totalBills(
                        billRepository.getTotalBills())

                .totalExpenses(
                        totalExpenses)

                .totalProfit(
                        totalProfit)

                .build();
    }

    @Override
    public List<RevenueChartDto>
    getRevenueChart(int year) {

        return transactionRepository
                .getMonthlyRevenueTrend(year)
                .stream()
                .map(data ->
                        RevenueChartDto.builder()
                                .month(
                                        Month.of(
                                                data.month())
                                                .getDisplayName(
                                                        TextStyle.SHORT,
                                                        Locale.ENGLISH))
                                .revenue(
                                        data.revenue())
                                .build())
                .toList();
    }

	@Override
	public List<TopItemDto> getTopGrindingItems() {
		return transactionRepository
				.getTopGrindingItems();
	}
	
	@Override
	public List<GrindingTransactionResponseDto>
	getRecentTransactions() {

	    return transactionRepository
	            .findTop5ByOrderByCreatedAtDesc()
	            .stream()
	            .map(transactionMapper::mapToResponse)
	            .toList();
	}
	
	@Override
	public RevenueExpenseDto getRevenueVsExpense() {

	    return RevenueExpenseDto.builder()
	            .revenue(
	                    billRepository.getTotalRevenue())
	            .expense(
	                    expenseRepository.getTotalExpenses())
	            .build();
	}
	
	
	@Override
	public List<TopExpenseDto>
	getTopExpenseCategories() {

	    return expenseRepository
	            .getTopExpenseCategories()
	            .stream()
	            .map(data ->
	                    TopExpenseDto.builder()
	                            .expenseType(
	                                    (String) data[0])
	                            .totalAmount(
	                                    (BigDecimal) data[1])
	                            .build())
	            .toList();
	}
	
	@Override
	public List<ProfitTrendDto>
	getProfitTrend() {

	    return List.of(

	            ProfitTrendDto.builder()
	                    .month("JAN")
	                    .profit(
	                            BigDecimal.valueOf(
	                                    25000))
	                    .build(),

	            ProfitTrendDto.builder()
	                    .month("FEB")
	                    .profit(
	                            BigDecimal.valueOf(
	                                    30000))
	                    .build(),

	            ProfitTrendDto.builder()
	                    .month("MAR")
	                    .profit(
	                            BigDecimal.valueOf(
	                                    45000))
	                    .build()
	    );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}