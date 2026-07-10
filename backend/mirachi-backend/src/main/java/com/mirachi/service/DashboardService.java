package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.DashboardSummaryDto;
import com.mirachi.dto.GrindingTransactionResponseDto;
import com.mirachi.dto.RevenueChartDto;
import com.mirachi.dto.TopItemDto;

public interface DashboardService {

    DashboardSummaryDto getDashboardSummary();

    List<RevenueChartDto> getRevenueChart(
            int year);
    
    List<TopItemDto> getTopGrindingItems();
    
    List<GrindingTransactionResponseDto>
    getRecentTransactions();
}