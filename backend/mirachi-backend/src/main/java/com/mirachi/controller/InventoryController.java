package com.mirachi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.InventoryRequestDto;
import com.mirachi.dto.InventoryResponseDto;
import com.mirachi.service.InventoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ApiResponse<InventoryResponseDto> createInventory(
            @Valid @RequestBody InventoryRequestDto requestDto) {

        return new ApiResponse<>(
                true,
                "Inventory created successfully",
                inventoryService.createInventory(requestDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<InventoryResponseDto> getInventoryById(
            @PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "Inventory fetched successfully",
                inventoryService.getInventoryById(id));
    }

    @GetMapping
    public ApiResponse<List<InventoryResponseDto>> getAllInventory() {

        return new ApiResponse<>(
                true,
                "Inventory list fetched successfully",
                inventoryService.getAllInventory());
    }

    @GetMapping("/low-stock")
    public ApiResponse<List<InventoryResponseDto>> getLowStockItems() {

        return new ApiResponse<>(
                true,
                "Low stock items fetched successfully",
                inventoryService.getLowStockItems());
    }

    @PutMapping("/{id}")
    public ApiResponse<InventoryResponseDto> updateInventory(
            @PathVariable Long id,
            @Valid @RequestBody InventoryRequestDto requestDto) {

        return new ApiResponse<>(
                true,
                "Inventory updated successfully",
                inventoryService.updateInventory(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteInventory(
            @PathVariable Long id) {

        inventoryService.deleteInventory(id);

        return new ApiResponse<>(
                true,
                "Inventory deleted successfully",
                "Deleted");
    }
}