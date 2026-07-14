package com.mirachi.mapper;

import org.springframework.stereotype.Component;

import com.mirachi.dto.inventory.InventoryTransactionResponseDto;
import com.mirachi.entity.InventoryTransaction;

@Component
public class InventoryTransactionMapper {

    public InventoryTransactionResponseDto toResponse(
            InventoryTransaction transaction) {

        return InventoryTransactionResponseDto.builder()
                .id(transaction.getId())
                .inventoryId(transaction.getInventory().getId())
                .itemName(transaction.getInventory().getItemName())
                .quantity(transaction.getQuantity())
                .transactionType(transaction.getTransactionType())
                .remarks(transaction.getRemarks())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}