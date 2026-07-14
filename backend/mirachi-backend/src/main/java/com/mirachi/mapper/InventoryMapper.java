package com.mirachi.mapper;

import org.springframework.stereotype.Component;

import com.mirachi.dto.InventoryRequestDto;
import com.mirachi.dto.InventoryResponseDto;
import com.mirachi.entity.Inventory;

@Component
public class InventoryMapper {

    public Inventory toEntity(InventoryRequestDto dto) {

        return Inventory.builder()
                .itemName(dto.getItemName())
                .availableQuantity(dto.getAvailableQuantity())
                .unit(dto.getUnit())
                .minimumStockLevel(dto.getMinimumStockLevel())
                .active(true)
                .build();
    }

    public InventoryResponseDto toResponse(Inventory inventory) {

        return InventoryResponseDto.builder()
                .id(inventory.getId())
                .itemName(inventory.getItemName())
                .availableQuantity(inventory.getAvailableQuantity())
                .unit(inventory.getUnit())
                .minimumStockLevel(inventory.getMinimumStockLevel())
                .active(inventory.getActive())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }
}