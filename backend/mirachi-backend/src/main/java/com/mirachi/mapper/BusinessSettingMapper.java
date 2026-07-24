package com.mirachi.mapper;

import org.springframework.stereotype.Component;

import com.mirachi.dto.BusinessSettingRequestDto;
import com.mirachi.dto.BusinessSettingResponseDto;
import com.mirachi.entity.BusinessSetting;

@Component
public class BusinessSettingMapper {

    public BusinessSetting toEntity(
            BusinessSettingRequestDto request) {

        return BusinessSetting.builder()
                .businessName(request.getBusinessName())
                .ownerName(request.getOwnerName())
                .address(request.getAddress())
                .mobileNumber(request.getMobileNumber())
                .whatsAppNumber(request.getWhatsAppNumber())
                .email(request.getEmail())
                .gstNumber(request.getGstNumber())
                .invoicePrefix(request.getInvoicePrefix())
                .currency(request.getCurrency())
                .logoUrl(request.getLogoUrl())
                .footerMessage(request.getFooterMessage())
                .build();
    }

    public BusinessSettingResponseDto toResponse(
            BusinessSetting setting) {

        return BusinessSettingResponseDto.builder()
                .id(setting.getId())
                .businessName(setting.getBusinessName())
                .ownerName(setting.getOwnerName())
                .address(setting.getAddress())
                .mobileNumber(setting.getMobileNumber())
                .whatsAppNumber(setting.getWhatsAppNumber())
                .email(setting.getEmail())
                .gstNumber(setting.getGstNumber())
                .invoicePrefix(setting.getInvoicePrefix())
                .currency(setting.getCurrency())
                .logoUrl(setting.getLogoUrl())
                .footerMessage(setting.getFooterMessage())
                .createdAt(setting.getCreatedAt())
                .updatedAt(setting.getUpdatedAt())
                .build();
    }
}