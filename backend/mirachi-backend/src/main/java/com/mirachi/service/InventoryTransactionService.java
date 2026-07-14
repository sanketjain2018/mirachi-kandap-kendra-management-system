package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.inventory.InventoryTransactionResponseDto;
import com.mirachi.dto.inventory.StockInRequestDto;
import com.mirachi.dto.inventory.StockOutRequestDto;

public interface InventoryTransactionService {

    InventoryTransactionResponseDto stockIn(
            StockInRequestDto requestDto);

    InventoryTransactionResponseDto stockOut(
            StockOutRequestDto requestDto);

    List<InventoryTransactionResponseDto>
    getInventoryHistory(Long inventoryId);
}