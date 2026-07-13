package com.mirachi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDto {

    // Revenue KPIs
    private BigDecimal todayRevenue;

    private BigDecimal monthRevenue;

    private BigDecimal yearRevenue;

    // Expense KPI
    private BigDecimal totalExpenses;

    // Profit KPI
    private BigDecimal totalProfit;

    // Business Counts
    private Long totalCustomers;

    private Long totalTransactions;

    private Long totalBills;

    private Long activeRates;
}