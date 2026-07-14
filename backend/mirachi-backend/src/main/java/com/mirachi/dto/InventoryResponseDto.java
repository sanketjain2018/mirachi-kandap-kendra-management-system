package com.mirachi.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponseDto {

    private Long id;

    private String itemName;

    private Double availableQuantity;

    private String unit;

    private Double minimumStockLevel;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}