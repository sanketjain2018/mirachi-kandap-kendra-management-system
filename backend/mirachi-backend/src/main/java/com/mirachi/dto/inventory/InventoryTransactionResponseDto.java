package com.mirachi.dto.inventory;

import java.time.LocalDateTime;

import com.mirachi.enums.TransactionType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryTransactionResponseDto {

    private Long id;

    private Long inventoryId;

    private String itemName;

    private Double quantity;

    private TransactionType transactionType;

    private String remarks;

    private LocalDateTime transactionDate;
}