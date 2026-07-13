package com.mirachi.dto;

import java.math.BigDecimal;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfitTrendDto {

    private String month;

    private BigDecimal profit;
}