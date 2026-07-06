package com.mirachi.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.CustomerRequestDto;
import com.mirachi.dto.CustomerResponseDto;
import com.mirachi.entity.Customer;
import com.mirachi.exception.CustomerNotFoundException;
import com.mirachi.exception.DuplicateCustomerException;
import com.mirachi.repository.CustomerRepository;
import com.mirachi.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	@Override
	public ApiResponse<CustomerResponseDto> createCustomer(CustomerRequestDto request) {

		customerRepository.findByMobileNumber(request.getMobileNumber()).ifPresent(customer -> {
			throw new DuplicateCustomerException("Mobile Number Already Exists");
		});

		Customer customer = Customer.builder().customerName(request.getCustomerName())
				.mobileNumber(request.getMobileNumber()).address(request.getAddress()).build();

		Customer savedCustomer = customerRepository.save(customer);
		
		 CustomerResponseDto response =
	                CustomerResponseDto.builder()
	                        .id(savedCustomer.getId())
	                        .customerName(
	                                savedCustomer.getCustomerName())
	                        .mobileNumber(
	                                savedCustomer.getMobileNumber())
	                        .address(
	                                savedCustomer.getAddress())
	                        .build();

		 return new ApiResponse<>(
	                true,
	                "Customer Created Successfully",
	                response);
	}

	@Override
    public ApiResponse<CustomerResponseDto>
    getCustomerById(Long id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer Not Found"));

        CustomerResponseDto response =
                CustomerResponseDto.builder()
                        .id(customer.getId())
                        .customerName(
                                customer.getCustomerName())
                        .mobileNumber(
                                customer.getMobileNumber())
                        .address(customer.getAddress())
                        .build();

        return new ApiResponse<>(
                true,
                "Customer Retrieved Successfully",
                response);
    }

	 @Override
	    public ApiResponse<List<CustomerResponseDto>>
	    getAllCustomers() {

	        List<CustomerResponseDto> customers =
	                customerRepository.findAll()
	                        .stream()
	                        .map(customer ->
	                                CustomerResponseDto.builder()
	                                        .id(customer.getId())
	                                        .customerName(
	                                                customer.getCustomerName())
	                                        .mobileNumber(
	                                                customer.getMobileNumber())
	                                        .address(
	                                                customer.getAddress())
	                                        .build())
	                        .toList();

	        return new ApiResponse<>(
	                true,
	                "Customers Retrieved Successfully",
	                customers);
	    }

	
	@Override
	public ApiResponse<CustomerResponseDto> updateCustomer(
	        Long id,
	        CustomerRequestDto request) {

	    Customer customer = customerRepository.findById(id)
	            .orElseThrow(() ->
	                    new CustomerNotFoundException(
	                            "Customer Not Found"));

	    customer.setCustomerName(
	            request.getCustomerName());

	    customer.setMobileNumber(
	            request.getMobileNumber());

	    customer.setAddress(
	            request.getAddress());

	    Customer updatedCustomer =
	            customerRepository.save(customer);

	    CustomerResponseDto response =
	            CustomerResponseDto.builder()
	                    .id(updatedCustomer.getId())
	                    .customerName(
	                            updatedCustomer.getCustomerName())
	                    .mobileNumber(
	                            updatedCustomer.getMobileNumber())
	                    .address(
	                            updatedCustomer.getAddress())
	                    .build();

	    return new ApiResponse<>(
	            true,
	            "Customer Updated Successfully",
	            response);
	}

	@Override
	public ApiResponse<Object> deleteCustomer(
	        Long id) {

	    Customer customer =
	            customerRepository.findById(id)
	                    .orElseThrow(() ->
	                            new CustomerNotFoundException(
	                                    "Customer Not Found"));

	    customerRepository.delete(customer);

	    return new ApiResponse<>(
	            true,
	            "Customer Deleted Successfully",
	            null);
	}

	@Override
	public ApiResponse<Page<CustomerResponseDto>>
	getCustomers(int page, int size) {

	    Pageable pageable =
	            PageRequest.of(page, size);

	    Page<CustomerResponseDto> customers =
	            customerRepository.findAll(pageable)
	                    .map(customer ->
	                            CustomerResponseDto.builder()
	                                    .id(customer.getId())
	                                    .customerName(
	                                            customer.getCustomerName())
	                                    .mobileNumber(
	                                            customer.getMobileNumber())
	                                    .address(
	                                            customer.getAddress())
	                                    .build());

	    return new ApiResponse<>(
	            true,
	            "Customers Retrieved Successfully",
	            customers);
	}

	@Override
	public ApiResponse<List<CustomerResponseDto>>
	searchCustomers(String customerName) {

	    List<CustomerResponseDto> customers =
	            customerRepository
	                    .findByCustomerNameContainingIgnoreCase(
	                            customerName)
	                    .stream()
	                    .map(customer ->
	                            CustomerResponseDto.builder()
	                                    .id(customer.getId())
	                                    .customerName(
	                                            customer.getCustomerName())
	                                    .mobileNumber(
	                                            customer.getMobileNumber())
	                                    .address(
	                                            customer.getAddress())
	                                    .build())
	                    .toList();

	    return new ApiResponse<>(
	            true,
	            "Customers Retrieved Successfully",
	            customers);
	}
	 

}
