package com.mirachi.mapper;

import org.springframework.stereotype.Component;

import com.mirachi.dto.audit.AuditLogResponseDto;
import com.mirachi.entity.AuditLog;

@Component
public class AuditLogMapper {

    public AuditLogResponseDto toResponse(
            AuditLog auditLog) {

        return AuditLogResponseDto.builder()
                .id(auditLog.getId())
                .username(auditLog.getUsername())
                .role(auditLog.getRole())
                .action(auditLog.getAction())
                .moduleName(auditLog.getModuleName())
                .description(auditLog.getDescription())
                .actionTime(auditLog.getActionTime())
                .build();
    }
}