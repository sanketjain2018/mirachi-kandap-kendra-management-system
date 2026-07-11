package com.mirachi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.BillRequestDto;
import com.mirachi.dto.BillResponseDto;
import com.mirachi.enums.PaymentStatus;
import com.mirachi.service.BillService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {
	 private final BillService billService;

	    @PostMapping
	    public ApiResponse<BillResponseDto>
	    createBill(
	            @Valid
	            @RequestBody
	            BillRequestDto request) {

	        return new ApiResponse<>(
	                true,
	                "Bill generated successfully",
	                billService.createBill(
	                        request));
	    }

	    @GetMapping("/{id}")
	    public ApiResponse<BillResponseDto>
	    getBillById(
	            @PathVariable Long id) {

	        return new ApiResponse<>(
	                true,
	                "Bill fetched successfully",
	                billService.getBillById(id));
	    }

	    @GetMapping
	    public ApiResponse<List<BillResponseDto>>
	    getAllBills() {

	        return new ApiResponse<>(
	                true,
	                "Bills fetched successfully",
	                billService.getAllBills());
	    }
	    
	    
	    @GetMapping("/number/{billNumber}")
	    public ApiResponse<BillResponseDto>
	    getBillByBillNumber(
	            @PathVariable String billNumber) {

	        return new ApiResponse<>(
	                true,
	                "Bill fetched successfully",
	                billService
	                        .getBillByBillNumber(
	                                billNumber));
	    }
	    
	    @GetMapping("/customer/{customerName}")
	    public ApiResponse<List<BillResponseDto>>
	    searchBillsByCustomerName(
	            @PathVariable String customerName) {

	        return new ApiResponse<>(
	                true,
	                "Bills fetched successfully",
	                billService
	                        .searchBillsByCustomerName(
	                                customerName));
	    }
	    
	    
	    @GetMapping("/status/{status}")
	    public ApiResponse<List<BillResponseDto>>
	    searchBillsByStatus(
	            @PathVariable PaymentStatus status) {

	        return new ApiResponse<>(
	                true,
	                "Bills fetched successfully",
	                billService
	                        .searchBillsByStatus(
	                                status));
	    }
	    
	    @GetMapping("/date-range")
	    public ApiResponse<List<BillResponseDto>>
	    searchBillsByDateRange(

	            @RequestParam
	            LocalDate startDate,

	            @RequestParam
	            LocalDate endDate) {

	        return new ApiResponse<>(
	                true,
	                "Bills fetched successfully",
	                billService
	                        .searchBillsByDateRange(
	                                startDate,
	                                endDate));
	    }
	    
	    @GetMapping("/customer-history/{customerId}")
	    public ApiResponse<List<BillResponseDto>>
	    getCustomerBillHistory(
	            @PathVariable Long customerId) {

	        return new ApiResponse<>(
	                true,
	                "Customer bill history fetched successfully",
	                billService
	                        .getCustomerBillHistory(
	                                customerId));
	    }
	    
	    
	    
	    
	    
}
