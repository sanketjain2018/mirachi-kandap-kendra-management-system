package com.mirachi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mirachi.dto.InventoryRequestDto;
import com.mirachi.dto.InventoryResponseDto;
import com.mirachi.entity.Inventory;
import com.mirachi.mapper.InventoryMapper;
import com.mirachi.repository.InventoryRepository;
import com.mirachi.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl
        implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final InventoryMapper inventoryMapper;

    @Override
    public InventoryResponseDto createInventory(
            InventoryRequestDto requestDto) {

        if (inventoryRepository.existsByItemNameIgnoreCase(
                requestDto.getItemName())) {

            throw new RuntimeException(
                    "Inventory item already exists");
        }

        Inventory inventory =
                inventoryMapper.toEntity(requestDto);

        return inventoryMapper.toResponse(
                inventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponseDto updateInventory(
            Long inventoryId,
            InventoryRequestDto requestDto) {

        Inventory inventory =
                inventoryRepository.findById(inventoryId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inventory not found"));

        inventory.setItemName(requestDto.getItemName());
        inventory.setAvailableQuantity(
                requestDto.getAvailableQuantity());
        inventory.setUnit(requestDto.getUnit());
        inventory.setMinimumStockLevel(
                requestDto.getMinimumStockLevel());

        return inventoryMapper.toResponse(
                inventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponseDto getInventoryById(
            Long inventoryId) {

        Inventory inventory =
                inventoryRepository.findById(inventoryId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inventory not found"));

        return inventoryMapper.toResponse(inventory);
    }

    @Override
    public List<InventoryResponseDto> getAllInventory() {

        return inventoryRepository.findByActiveTrue()
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<InventoryResponseDto> getLowStockItems() {

        return inventoryRepository.findAll()
                .stream()
                .filter(i -> i.getAvailableQuantity()
                        <= i.getMinimumStockLevel())
                .map(inventoryMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteInventory(Long inventoryId) {

        Inventory inventory =
                inventoryRepository.findById(inventoryId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inventory not found"));

        inventory.setActive(false);

        inventoryRepository.save(inventory);
    }
}