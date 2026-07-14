package com.mirachi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.InventoryTransaction;

public interface InventoryTransactionRepository
        extends JpaRepository<InventoryTransaction, Long> {

    List<InventoryTransaction> findByInventoryIdOrderByTransactionDateDesc(
            Long inventoryId);
}