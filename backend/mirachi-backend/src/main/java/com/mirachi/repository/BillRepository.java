package com.mirachi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.Bill;

public interface BillRepository
        extends JpaRepository<Bill, Long> {

    Optional<Bill> findByBillNumber(
            String billNumber);
}