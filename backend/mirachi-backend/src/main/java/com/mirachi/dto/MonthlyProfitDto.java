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
public class MonthlyProfitDto {

    private String month;

    private BigDecimal revenue;

    private BigDecimal expense;

    private BigDecimal profit;
}