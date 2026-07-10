package com.mirachi.dto;

import java.math.BigDecimal;

public record MonthlyRevenueDto(
        Integer month,
        BigDecimal revenue) {
}