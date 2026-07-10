package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.BillRequestDto;
import com.mirachi.dto.BillResponseDto;

public interface BillService {

    BillResponseDto createBill(
            BillRequestDto request);

    List<BillResponseDto> getAllBills();

    BillResponseDto getBillById(
            Long billId);
}