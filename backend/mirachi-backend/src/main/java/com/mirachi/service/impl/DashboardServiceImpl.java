package com.mirachi.service.impl;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.mirachi.dto.DashboardSummaryDto;
import com.mirachi.dto.GrindingTransactionResponseDto;
import com.mirachi.dto.RevenueChartDto;
import com.mirachi.dto.TopItemDto;
import com.mirachi.mapper.GrindingTransactionMapper;
import com.mirachi.repository.CustomerRepository;
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
    @Override
    public DashboardSummaryDto getDashboardSummary() {

        LocalDate today = LocalDate.now();

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
}