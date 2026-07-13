package com.mirachi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mirachi.dto.MonthlyProfitDto;
import com.mirachi.dto.ProfitDateRangeDto;
import com.mirachi.dto.ProfitSummaryDto;
import com.mirachi.dto.RevenueExpenseDto;
import com.mirachi.dto.TopExpenseDto;
import com.mirachi.repository.BillRepository;
import com.mirachi.repository.ExpenseRepository;
import com.mirachi.repository.GrindingTransactionRepository;
import com.mirachi.service.ProfitService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfitServiceImpl implements ProfitService {

    private final ExpenseRepository expenseRepository;
    private final GrindingTransactionRepository grindingTransactionRepository;
    private final BillRepository billRepository;

    @Override
    public ProfitSummaryDto getProfitSummary() {

	BigDecimal revenue = grindingTransactionRepository.getTotalRevenue();

	BigDecimal expense = expenseRepository.getTotalExpenses();

	BigDecimal profit = revenue.subtract(expense);

	return ProfitSummaryDto.builder().totalRevenue(revenue).totalExpense(expense).totalProfit(profit).build();
    }
    
    @Override
    public MonthlyProfitDto getCurrentMonthProfit() {

        LocalDate today = LocalDate.now();

        int year = today.getYear();

        int month = today.getMonthValue();

        BigDecimal revenue =
                billRepository
                        .getMonthlyRevenue(
                                year,
                                month);

        BigDecimal expense =
                expenseRepository
                        .getMonthlyExpense(
                                year,
                                month);

        BigDecimal profit =
                revenue.subtract(expense);

        return MonthlyProfitDto
                .builder()
                .month(
                        today.getMonth()
                             .name())
                .revenue(revenue)
                .expense(expense)
                .profit(profit)
                .build();
    }
    
    @Override
    public ProfitDateRangeDto getProfitBetweenDates(
            LocalDate startDate,
            LocalDate endDate) {

        BigDecimal revenue =
                billRepository
                        .getRevenueBetweenDates(
                                startDate,
                                endDate);

        BigDecimal expense =
                expenseRepository
                        .getExpenseBetweenDates(
                                startDate,
                                endDate);

        return ProfitDateRangeDto.builder()
                .startDate(startDate)
                .endDate(endDate)
                .revenue(revenue)
                .expense(expense)
                .profit(revenue.subtract(expense))
                .build();
    }
    
    @Override
    public RevenueExpenseDto getRevenueVsExpense() {

        BigDecimal revenue =
                billRepository.getTotalRevenue();

        BigDecimal expense =
                expenseRepository.getTotalExpenses();

        return RevenueExpenseDto.builder()
                .revenue(revenue)
                .expense(expense)
                .build();
    }
    
    @Override
    public List<TopExpenseDto>
    getTopExpenseCategories() {

        return expenseRepository
                .getTopExpenseCategories()
                .stream()
                .map(obj ->
                        TopExpenseDto.builder()
                                .expenseType(
                                        (String) obj[0])
                                .totalAmount(
                                        (BigDecimal) obj[1])
                                .build())
                .toList();
    }

}
