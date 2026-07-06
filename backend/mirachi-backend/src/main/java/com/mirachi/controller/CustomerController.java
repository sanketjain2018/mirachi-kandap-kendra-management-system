package com.mirachi.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.CustomerRequestDto;
import com.mirachi.dto.CustomerResponseDto;
import com.mirachi.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ApiResponse<CustomerResponseDto>
    createCustomer(
            @Valid
            @RequestBody CustomerRequestDto request) {

        return customerService.createCustomer(
                request);
    }

    @GetMapping("/{id}")
    public ApiResponse<CustomerResponseDto>
    getCustomerById(
            @PathVariable Long id) {

        return customerService.getCustomerById(id);
    }

    @GetMapping
    public ApiResponse<List<CustomerResponseDto>>
    getAllCustomers() {

        return customerService.getAllCustomers();
    }
    
    @PutMapping("/{id}")
    public ApiResponse<CustomerResponseDto>
    updateCustomer(
            @PathVariable Long id,
            @Valid
            @RequestBody CustomerRequestDto request) {

        return customerService.updateCustomer(
                id,
                request);
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteCustomer(
            @PathVariable Long id) {

        return customerService.deleteCustomer(id);
    }
}