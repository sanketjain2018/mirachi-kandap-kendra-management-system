package com.mirachi.dto;

import java.math.BigDecimal;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillItemResponseDto {

    private String itemName;

    private Double weightInKg;

    private BigDecimal ratePerKg;

    private BigDecimal amount;
}