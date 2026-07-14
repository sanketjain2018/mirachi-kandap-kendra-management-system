package com.mirachi.dto.audit;

import java.time.LocalDateTime;

import com.mirachi.enums.AuditAction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditLogResponseDto {

    private Long id;

    private String username;

    private String role;

    private AuditAction action;

    private String moduleName;

    private String description;

    private LocalDateTime actionTime;
}