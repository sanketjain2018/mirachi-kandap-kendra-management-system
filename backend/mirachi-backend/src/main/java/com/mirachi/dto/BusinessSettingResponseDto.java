package com.mirachi.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessSettingResponseDto {

    private Long id;

    private String businessName;

    private String ownerName;

    private String address;

    private String mobileNumber;

    private String whatsAppNumber;

    private String email;

    private String gstNumber;

    private String invoicePrefix;

    private String currency;

    private String logoUrl;

    private String footerMessage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}