package com.mirachi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BusinessSettingRequestDto {

    @NotBlank(message = "Business name is required")
    @Size(max = 150)
    private String businessName;

    @NotBlank(message = "Owner name is required")
    @Size(max = 150)
    private String ownerName;

    @NotBlank(message = "Address is required")
    @Size(max = 500)
    private String address;

    @NotBlank(message = "Mobile number is required")
    @Size(max = 15)
    private String mobileNumber;

    @Size(max = 15)
    private String whatsAppNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @Size(max = 30)
    private String gstNumber;

    @NotBlank(message = "Invoice prefix is required")
    @Size(max = 20)
    private String invoicePrefix;

    @NotBlank(message = "Currency is required")
    @Size(max = 20)
    private String currency;

    private String logoUrl;

    @Size(max = 1000)
    private String footerMessage;
}