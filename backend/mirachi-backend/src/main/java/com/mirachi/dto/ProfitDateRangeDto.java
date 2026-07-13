package com.mirachi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfitDateRangeDto {
    
    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal revenue;

    private BigDecimal expense;

    private BigDecimal profit;
}
