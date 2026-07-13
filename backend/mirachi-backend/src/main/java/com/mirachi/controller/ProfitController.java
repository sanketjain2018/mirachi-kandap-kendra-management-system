package com.mirachi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.ProfitSummaryDto;
import com.mirachi.service.ProfitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profit/")
@RequiredArgsConstructor
public class ProfitController {
    
    private final ProfitService profitService;
    
    @GetMapping("/summary")
    public ApiResponse<ProfitSummaryDto> getProfitSummary(){
	return new ApiResponse<>(
		true,
                "Profit summary fetched successfully",
                profitService.getProfitSummary());
    }
}

