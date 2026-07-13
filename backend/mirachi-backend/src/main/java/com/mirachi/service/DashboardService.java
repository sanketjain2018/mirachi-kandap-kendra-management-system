package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.DashboardSummaryDto;
import com.mirachi.dto.GrindingTransactionResponseDto;
import com.mirachi.dto.ProfitTrendDto;
import com.mirachi.dto.RevenueChartDto;
import com.mirachi.dto.RevenueExpenseDto;
import com.mirachi.dto.TopExpenseDto;
import com.mirachi.dto.TopItemDto;

public interface DashboardService {

    DashboardSummaryDto getDashboardSummary();

    List<RevenueChartDto> getRevenueChart(
            int year);
    
    List<TopItemDto> getTopGrindingItems();
    
    List<GrindingTransactionResponseDto>
    getRecentTransactions();
    
    RevenueExpenseDto getRevenueVsExpense();

    List<TopExpenseDto> getTopExpenseCategories();

    List<ProfitTrendDto> getProfitTrend();
}