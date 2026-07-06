package com.mirachi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("/page")
    public ApiResponse<Page<CustomerResponseDto>>
    getCustomers(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        return customerService
                .getCustomers(page, size);
    }
    
    @GetMapping("/search")
    public ApiResponse<List<CustomerResponseDto>>
    searchCustomers(
            @RequestParam String name) {

        return customerService
                .searchCustomers(name);
    }
}