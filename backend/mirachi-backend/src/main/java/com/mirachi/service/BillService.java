package com.mirachi.service;

import java.time.LocalDate;
import java.util.List;

import com.mirachi.dto.BillRequestDto;
import com.mirachi.dto.BillResponseDto;
import com.mirachi.enums.PaymentStatus;

public interface BillService {

    BillResponseDto createBill(
            BillRequestDto request);

    List<BillResponseDto> getAllBills();

    BillResponseDto getBillById(
            Long billId);
    
    BillResponseDto
    getBillByBillNumber(
            String billNumber);

    List<BillResponseDto>
    searchBillsByCustomerName(
            String customerName);

    List<BillResponseDto>
    searchBillsByStatus(
            PaymentStatus status);

    List<BillResponseDto>
    searchBillsByDateRange(
            LocalDate startDate,
            LocalDate endDate);
    
    List<BillResponseDto>
    getCustomerBillHistory(
            Long customerId);
}