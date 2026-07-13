package com.mirachi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAdminRequestDto {

    @NotBlank
    private String fullName;

    @Email
    private String email;

    @NotBlank
    private String password;
}