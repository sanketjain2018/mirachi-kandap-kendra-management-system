package com.mirachi.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.RateMasterRequestDto;
import com.mirachi.dto.RateMasterResponseDto;
import com.mirachi.service.RateMasterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor
@Validated
public class RateMasterController {

    private final RateMasterService rateMasterService;

    @PostMapping
    public ApiResponse<RateMasterResponseDto>
    createRate(
            @Valid
            @RequestBody
            RateMasterRequestDto request) {

        return rateMasterService.createRate(request);
    }

    @GetMapping
    public ApiResponse<List<RateMasterResponseDto>>
    getAllRates() {

        return rateMasterService.getAllRates();
    }

    @GetMapping("/{id}")
    public ApiResponse<RateMasterResponseDto>
    getRateById(
            @PathVariable Long id) {

        return rateMasterService.getRateById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse<RateMasterResponseDto>
    updateRate(
            @PathVariable Long id,
            @Valid
            @RequestBody
            RateMasterRequestDto request) {

        return rateMasterService.updateRate(
                id,
                request);
    }

    @PatchMapping("/{id}/deactivate")
    public ApiResponse<String>
    deactivateRate(
            @PathVariable Long id) {

        return rateMasterService.deactivateRate(id);
    }
}