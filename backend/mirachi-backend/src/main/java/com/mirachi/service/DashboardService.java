package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.DashboardSummaryDto;
import com.mirachi.dto.RevenueChartDto;

public interface DashboardService {

    DashboardSummaryDto getDashboardSummary();

    List<RevenueChartDto> getRevenueChart(
            int year);
}