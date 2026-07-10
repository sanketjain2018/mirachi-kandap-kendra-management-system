package com.mirachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.BillItem;

public interface BillItemRepository
        extends JpaRepository<BillItem, Long> {

}