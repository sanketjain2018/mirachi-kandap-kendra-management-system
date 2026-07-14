package com.mirachi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.inventory.InventoryTransactionResponseDto;
import com.mirachi.dto.inventory.StockInRequestDto;
import com.mirachi.dto.inventory.StockOutRequestDto;
import com.mirachi.service.InventoryTransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryTransactionController {

    private final InventoryTransactionService
            inventoryTransactionService;

    @PostMapping("/stock-in")
    public ApiResponse<InventoryTransactionResponseDto>
    stockIn(
            @Valid
            @RequestBody
            StockInRequestDto requestDto) {

        return new ApiResponse<>(
                true,
                "Stock added successfully",
                inventoryTransactionService.stockIn(
                        requestDto));
    }

    @PostMapping("/stock-out")
    public ApiResponse<InventoryTransactionResponseDto>
    stockOut(
            @Valid
            @RequestBody
            StockOutRequestDto requestDto) {

        return new ApiResponse<>(
                true,
                "Stock removed successfully",
                inventoryTransactionService.stockOut(
                        requestDto));
    }

    @GetMapping("/{inventoryId}/history")
    public ApiResponse<
            List<InventoryTransactionResponseDto>>
    getHistory(
            @PathVariable Long inventoryId) {

        return new ApiResponse<>(
                true,
                "Inventory history fetched successfully",
                inventoryTransactionService
                        .getInventoryHistory(
                                inventoryId));
    }
}