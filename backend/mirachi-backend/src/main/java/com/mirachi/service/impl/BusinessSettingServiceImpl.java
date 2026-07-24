package com.mirachi.service.impl;

import org.springframework.stereotype.Service;

import com.mirachi.dto.BusinessSettingRequestDto;
import com.mirachi.dto.BusinessSettingResponseDto;
import com.mirachi.entity.BusinessSetting;
import com.mirachi.enums.AuditAction;
import com.mirachi.exception.BusinessSettingAlreadyExistsException;
import com.mirachi.exception.BusinessSettingNotFoundException;
import com.mirachi.mapper.BusinessSettingMapper;
import com.mirachi.repository.BusinessSettingRepository;
import com.mirachi.service.AuditLogService;
import com.mirachi.service.BusinessSettingService;
import com.mirachi.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessSettingServiceImpl
        implements BusinessSettingService {

    private final BusinessSettingRepository
            businessSettingRepository;

    private final BusinessSettingMapper
            businessSettingMapper;

    private final AuditLogService
            auditLogService;

    @Override
    public BusinessSettingResponseDto createBusinessSetting(
            BusinessSettingRequestDto request) {

	if (businessSettingRepository
	        .findTopByOrderByIdAsc()
	        .isPresent()) {

	    throw new BusinessSettingAlreadyExistsException(
	            "Business settings already exist.");
	}

        BusinessSetting setting =
                businessSettingMapper.toEntity(request);

        BusinessSetting savedSetting =
                businessSettingRepository.save(setting);

        auditLogService.logAction(
                SecurityUtil.getCurrentUsername(),
                SecurityUtil.getCurrentRole(),
                AuditAction.CREATE,
                "Business Settings",
                "Created business settings");

        return businessSettingMapper.toResponse(savedSetting);
    }

    @Override
    public BusinessSettingResponseDto getBusinessSetting() {

        BusinessSetting setting =
                businessSettingRepository
                        .findTopByOrderByIdAsc()
                        .orElseThrow(() ->
                                new BusinessSettingNotFoundException(
                                        "Business settings not found"));

        return businessSettingMapper.toResponse(setting);
    }

    @Override
    public BusinessSettingResponseDto updateBusinessSetting(
            BusinessSettingRequestDto request) {

        BusinessSetting setting =
                businessSettingRepository
                        .findTopByOrderByIdAsc()
                        .orElseThrow(() ->
                                new BusinessSettingNotFoundException(
                                        "Business settings not found"));

        setting.setBusinessName(request.getBusinessName());
        setting.setOwnerName(request.getOwnerName());
        setting.setAddress(request.getAddress());
        setting.setMobileNumber(request.getMobileNumber());
        setting.setWhatsAppNumber(request.getWhatsAppNumber());
        setting.setEmail(request.getEmail());
        setting.setGstNumber(request.getGstNumber());
        setting.setInvoicePrefix(request.getInvoicePrefix());
        setting.setCurrency(request.getCurrency());
        setting.setLogoUrl(request.getLogoUrl());
        setting.setFooterMessage(request.getFooterMessage());

        BusinessSetting updatedSetting =
                businessSettingRepository.save(setting);

        auditLogService.logAction(
                SecurityUtil.getCurrentUsername(),
                SecurityUtil.getCurrentRole(),
                AuditAction.UPDATE,
                "Business Settings",
                "Updated business settings");

        return businessSettingMapper.toResponse(updatedSetting);
    }
}