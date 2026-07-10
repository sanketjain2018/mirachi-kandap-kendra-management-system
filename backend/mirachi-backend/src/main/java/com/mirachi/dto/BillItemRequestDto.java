package com.mirachi.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillItemRequestDto {

    @NotBlank(message = "Item name is required")
    private String itemName;

    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be greater than zero")
    private Double weightInKg;

    @NotNull(message = "Rate is required")
    @Positive(message = "Rate must be greater than zero")
    private BigDecimal ratePerKg;
}