package com.mirachi.service;

import java.time.LocalDate;
import java.util.List;

import com.mirachi.dto.MonthlyProfitDto;
import com.mirachi.dto.ProfitDateRangeDto;
import com.mirachi.dto.ProfitSummaryDto;
import com.mirachi.dto.RevenueExpenseDto;
import com.mirachi.dto.TopExpenseDto;

public interface ProfitService {
    
    ProfitSummaryDto getProfitSummary();
    MonthlyProfitDto getCurrentMonthProfit();
    ProfitDateRangeDto getProfitBetweenDates(
            LocalDate startDate,
            LocalDate endDate);

    RevenueExpenseDto getRevenueVsExpense();
    

    List<TopExpenseDto> getTopExpenseCategories();
}
