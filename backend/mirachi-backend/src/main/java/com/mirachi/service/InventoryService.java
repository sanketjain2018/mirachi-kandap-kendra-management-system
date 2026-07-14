package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.InventoryRequestDto;
import com.mirachi.dto.InventoryResponseDto;

public interface InventoryService {

    InventoryResponseDto createInventory(
            InventoryRequestDto requestDto);

    InventoryResponseDto updateInventory(
            Long inventoryId,
            InventoryRequestDto requestDto);

    InventoryResponseDto getInventoryById(
            Long inventoryId);

    List<InventoryResponseDto> getAllInventory();

    List<InventoryResponseDto> getLowStockItems();

    void deleteInventory(Long inventoryId);
}