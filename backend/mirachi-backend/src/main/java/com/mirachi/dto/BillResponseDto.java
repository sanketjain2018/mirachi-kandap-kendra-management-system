package com.mirachi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillResponseDto {

    private Long id;

    private String billNumber;

    private String customerName;

    private LocalDate billDate;

    private BigDecimal totalAmount;

    private String paymentStatus;

    private String notes;

    private List<BillItemResponseDto> items;
}