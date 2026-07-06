package com.mirachi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.RateMasterRequestDto;
import com.mirachi.dto.RateMasterResponseDto;
import com.mirachi.entity.RateMaster;
import com.mirachi.exception.RateMasterNotFoundException;
import com.mirachi.repository.RateMasterRepository;
import com.mirachi.service.RateMasterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RateMasterServiceImpl implements RateMasterService {

    private final RateMasterRepository rateMasterRepository;

    @Override
    public ApiResponse<RateMasterResponseDto> createRate(
            RateMasterRequestDto request) {

        if (rateMasterRepository.existsByItemNameIgnoreCase(
                request.getItemName())) {

            throw new RuntimeException(
                    "Rate already exists for item : "
                            + request.getItemName());
        }

        RateMaster rateMaster = RateMaster.builder()
                .itemName(request.getItemName())
                .ratePerKg(request.getRatePerKg())
                .description(request.getDescription())
                .active(true)
                .build();

        RateMaster savedRate =
                rateMasterRepository.save(rateMaster);

        return new ApiResponse<>(
                true,
                "Rate created successfully",
                mapToResponse(savedRate));
    }

    @Override
    public ApiResponse<List<RateMasterResponseDto>>
    getAllRates() {

        List<RateMasterResponseDto> rates =
                rateMasterRepository.findAll()
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        return new ApiResponse<>(
                true,
                "Rates fetched successfully",
                rates);
    }

    @Override
    public ApiResponse<RateMasterResponseDto>
    getRateById(Long id) {

        RateMaster rateMaster =
                rateMasterRepository.findById(id)
                        .orElseThrow(() ->
                                new RateMasterNotFoundException(
                                        "Rate not found with id : "
                                                + id));

        return new ApiResponse<>(
                true,
                "Rate fetched successfully",
                mapToResponse(rateMaster));
    }

    @Override
    public ApiResponse<RateMasterResponseDto>
    updateRate(Long id,
               RateMasterRequestDto request) {

        RateMaster rateMaster =
                rateMasterRepository.findById(id)
                        .orElseThrow(() ->
                                new RateMasterNotFoundException(
                                        "Rate not found with id : "
                                                + id));

        rateMaster.setItemName(
                request.getItemName());

        rateMaster.setRatePerKg(
                request.getRatePerKg());

        rateMaster.setDescription(
                request.getDescription());

        RateMaster updatedRate =
                rateMasterRepository.save(rateMaster);

        return new ApiResponse<>(
                true,
                "Rate updated successfully",
                mapToResponse(updatedRate));
    }

    @Override
    public ApiResponse<String>
    deactivateRate(Long id) {

        RateMaster rateMaster =
                rateMasterRepository.findById(id)
                        .orElseThrow(() ->
                                new RateMasterNotFoundException(
                                        "Rate not found with id : "
                                                + id));

        rateMaster.setActive(false);

        rateMasterRepository.save(rateMaster);

        return new ApiResponse<>(
                true,
                "Rate deactivated successfully",
                null);
    }

    private RateMasterResponseDto mapToResponse(
            RateMaster rateMaster) {

        return RateMasterResponseDto.builder()
                .id(rateMaster.getId())
                .itemName(rateMaster.getItemName())
                .ratePerKg(rateMaster.getRatePerKg())
                .description(rateMaster.getDescription())
                .active(rateMaster.isActive())
                .createdAt(rateMaster.getCreatedAt())
                .updatedAt(rateMaster.getUpdatedAt())
                .build();
    }
}