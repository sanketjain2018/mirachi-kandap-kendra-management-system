package com.mirachi.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminResponseDto {

    private Long id;

    private String fullName;

    private String email;

    private Boolean enabled;

    private LocalDateTime lastLoginAt;
}