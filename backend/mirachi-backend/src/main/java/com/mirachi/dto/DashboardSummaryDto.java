package com.mirachi.dto;

import java.math.BigDecimal;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDto {

    private BigDecimal todayRevenue;

    private BigDecimal monthRevenue;

    private BigDecimal yearRevenue;

    private Long totalCustomers;

    private Long totalTransactions;

    private Long activeRates;
}