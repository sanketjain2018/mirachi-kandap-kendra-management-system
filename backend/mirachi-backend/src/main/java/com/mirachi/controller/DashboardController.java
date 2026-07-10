package com.mirachi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.DashboardSummaryDto;
import com.mirachi.dto.RevenueChartDto;
import com.mirachi.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ApiResponse<DashboardSummaryDto>
    getDashboardSummary() {

        return new ApiResponse<>(
                true,
                "Dashboard summary fetched successfully",
                dashboardService
                        .getDashboardSummary());
    }

    @GetMapping("/revenue-chart")
    public ApiResponse<List<RevenueChartDto>>
    getRevenueChart(
            @RequestParam int year) {

        return new ApiResponse<>(
                true,
                "Revenue chart fetched successfully",
                dashboardService
                        .getRevenueChart(year));
    }
}