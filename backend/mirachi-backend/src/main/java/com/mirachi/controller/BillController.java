package com.mirachi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.BillRequestDto;
import com.mirachi.dto.BillResponseDto;
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
}
