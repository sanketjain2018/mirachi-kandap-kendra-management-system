package com.mirachi.dto;

import java.util.List;

import com.mirachi.enums.PaymentStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillRequestDto {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    private PaymentStatus paymentStatus;

    private String notes;

    @Valid
    @NotEmpty(message = "At least one bill item is required")
    private List<BillItemRequestDto> items;
}