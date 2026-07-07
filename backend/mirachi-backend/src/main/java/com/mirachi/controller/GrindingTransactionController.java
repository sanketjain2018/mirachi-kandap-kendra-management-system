

package com.mirachi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.GrindingTransactionRequestDto;
import com.mirachi.dto.GrindingTransactionResponseDto;
import com.mirachi.service.GrindingTransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class GrindingTransactionController {

    private final GrindingTransactionService
            transactionService;

    @PostMapping
    public ApiResponse<GrindingTransactionResponseDto>
    createTransaction(
            @Valid
            @RequestBody
            GrindingTransactionRequestDto request) {

        return transactionService
                .createTransaction(request);
    }

    @GetMapping
    public ApiResponse<List<GrindingTransactionResponseDto>>
    getAllTransactions() {

        return transactionService
                .getAllTransactions();
    }

    @GetMapping("/{id}")
    public ApiResponse<GrindingTransactionResponseDto>
    getTransactionById(
            @PathVariable Long id) {

        return transactionService
                .getTransactionById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse<GrindingTransactionResponseDto>
    updateTransaction(
            @PathVariable Long id,
            @Valid
            @RequestBody
            GrindingTransactionRequestDto request) {

        return transactionService
                .updateTransaction(id, request);
    }

    @PatchMapping("/{id}/cancel")
    public ApiResponse<String>
    cancelTransaction(
            @PathVariable Long id) {

        return transactionService
                .cancelTransaction(id);
    }
    
    
    @GetMapping("/customer/{customerId}")
    public ApiResponse<List<GrindingTransactionResponseDto>>
    getTransactionsByCustomer(
            @PathVariable Long customerId) {

        return transactionService
                .getTransactionsByCustomer(customerId);
    }
    
    @GetMapping("/rate/{rateMasterId}")
    public ApiResponse<List<GrindingTransactionResponseDto>>
    getTransactionsByRate(
            @PathVariable Long rateMasterId) {

        return transactionService
                .getTransactionsByRate(rateMasterId);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}