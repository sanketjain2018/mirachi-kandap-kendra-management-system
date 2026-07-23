package com.mirachi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mirachi.dto.inventory.InventoryTransactionResponseDto;
import com.mirachi.dto.inventory.StockInRequestDto;
import com.mirachi.dto.inventory.StockOutRequestDto;
import com.mirachi.entity.Inventory;
import com.mirachi.entity.InventoryTransaction;
import com.mirachi.enums.AuditAction;
import com.mirachi.enums.TransactionType;
import com.mirachi.mapper.InventoryTransactionMapper;
import com.mirachi.repository.InventoryRepository;
import com.mirachi.repository.InventoryTransactionRepository;
import com.mirachi.service.AuditLogService;
import com.mirachi.service.InventoryTransactionService;
import com.mirachi.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryTransactionServiceImpl
        implements InventoryTransactionService {

    private final InventoryRepository inventoryRepository;

    private final InventoryTransactionRepository
            inventoryTransactionRepository;

    private final InventoryTransactionMapper
            inventoryTransactionMapper;

    private final AuditLogService auditLogService;

    @Override
    public InventoryTransactionResponseDto stockIn(
            StockInRequestDto requestDto) {

        Inventory inventory =
                inventoryRepository.findById(
                        requestDto.getInventoryId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inventory not found"));

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity()
                        + requestDto.getQuantity());

        inventoryRepository.save(inventory);

        InventoryTransaction transaction =
                InventoryTransaction.builder()
                        .inventory(inventory)
                        .quantity(requestDto.getQuantity())
                        .transactionType(
                                TransactionType.STOCK_IN)
                        .remarks(requestDto.getRemarks())
                        .build();

        InventoryTransaction savedTransaction =
                inventoryTransactionRepository.save(
                        transaction);

        auditLogService.logAction(
                SecurityUtil.getCurrentUsername(),
                SecurityUtil.getCurrentRole(),
                AuditAction.STOCK_IN,
                "Inventory",
                requestDto.getQuantity()
                        + " units added to inventory item "
                        + inventory.getItemName());

        return inventoryTransactionMapper.toResponse(
                savedTransaction);
    }

    @Override
    public InventoryTransactionResponseDto stockOut(
            StockOutRequestDto requestDto) {

        Inventory inventory =
                inventoryRepository.findById(
                        requestDto.getInventoryId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inventory not found"));

        if (inventory.getAvailableQuantity()
                < requestDto.getQuantity()) {

            throw new RuntimeException(
                    "Insufficient stock available");
        }

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity()
                        - requestDto.getQuantity());

        inventoryRepository.save(inventory);

        InventoryTransaction transaction =
                InventoryTransaction.builder()
                        .inventory(inventory)
                        .quantity(requestDto.getQuantity())
                        .transactionType(
                                TransactionType.STOCK_OUT)
                        .remarks(requestDto.getRemarks())
                        .build();

        InventoryTransaction savedTransaction =
                inventoryTransactionRepository.save(
                        transaction);

        auditLogService.logAction(
                SecurityUtil.getCurrentUsername(),
                SecurityUtil.getCurrentRole(),
                AuditAction.STOCK_OUT,
                "Inventory",
                requestDto.getQuantity()
                        + " units removed from inventory item "
                        + inventory.getItemName());

        return inventoryTransactionMapper.toResponse(
                savedTransaction);
    }

    @Override
    public List<InventoryTransactionResponseDto>
    getInventoryHistory(Long inventoryId) {

        return inventoryTransactionRepository
                .findByInventoryIdOrderByTransactionDateDesc(
                        inventoryId)
                .stream()
                .map(inventoryTransactionMapper::toResponse)
                .toList();
    }
}