package com.mirachi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.CustomerRequestDto;
import com.mirachi.dto.CustomerResponseDto;

public interface CustomerService {

	ApiResponse<CustomerResponseDto> createCustomer(CustomerRequestDto request);

	ApiResponse<CustomerResponseDto> getCustomerById(Long id);

	ApiResponse<List<CustomerResponseDto>> getAllCustomers();

	ApiResponse<CustomerResponseDto> updateCustomer(Long id, CustomerRequestDto request);

	ApiResponse<Object> deleteCustomer(Long id);

	ApiResponse<Page<CustomerResponseDto>> getCustomers(int page, int size);

	ApiResponse<List<CustomerResponseDto>> searchCustomers(String customerName);

}
