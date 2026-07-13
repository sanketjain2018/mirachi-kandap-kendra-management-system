package com.mirachi.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.mirachi.dto.ProfitSummaryDto;
import com.mirachi.repository.ExpenseRepository;
import com.mirachi.repository.GrindingTransactionRepository;
import com.mirachi.service.ProfitService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfitServiceImpl implements ProfitService {

    private final ExpenseRepository expenseRepository;
    private final GrindingTransactionRepository grindingTransactionRepository;

    @Override
    public ProfitSummaryDto getProfitSummary() {

	BigDecimal revenue = grindingTransactionRepository.getTotalRevenue();

	BigDecimal expense = expenseRepository.getTotalExpenses();

	BigDecimal profit = revenue.subtract(expense);

	return ProfitSummaryDto.builder().totalRevenue(revenue).totalExpense(expense).totalProfit(profit).build();
    }

}
