package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.RateMasterRequestDto;
import com.mirachi.dto.RateMasterResponseDto;

public interface RateMasterService {

    ApiResponse<RateMasterResponseDto> createRate(
            RateMasterRequestDto request);

    ApiResponse<List<RateMasterResponseDto>> getAllRates();

    ApiResponse<RateMasterResponseDto> getRateById(
            Long id);

    ApiResponse<RateMasterResponseDto> updateRate(
            Long id,
            RateMasterRequestDto request);

    ApiResponse<String> deactivateRate(
            Long id);
}