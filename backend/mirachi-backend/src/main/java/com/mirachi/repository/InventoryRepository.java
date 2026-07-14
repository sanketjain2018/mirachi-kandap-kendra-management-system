package com.mirachi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.Inventory;

public interface InventoryRepository
        extends JpaRepository<Inventory, Long> {

    boolean existsByItemNameIgnoreCase(String itemName);

    List<Inventory> findByActiveTrue();

    List<Inventory> findByAvailableQuantityLessThanEqual(
            Double minimumStockLevel);
}