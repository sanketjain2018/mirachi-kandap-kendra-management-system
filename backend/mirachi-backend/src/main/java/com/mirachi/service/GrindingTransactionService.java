package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.GrindingTransactionRequestDto;
import com.mirachi.dto.GrindingTransactionResponseDto;

public interface GrindingTransactionService {

    ApiResponse<GrindingTransactionResponseDto>
    createTransaction(
            GrindingTransactionRequestDto request);

    ApiResponse<List<GrindingTransactionResponseDto>>
    getAllTransactions();

    ApiResponse<GrindingTransactionResponseDto>
    getTransactionById(Long id);

    ApiResponse<GrindingTransactionResponseDto>
    updateTransaction(
            Long id,
            GrindingTransactionRequestDto request);

    ApiResponse<String>
    cancelTransaction(Long id);
}