package com.mirachi.service;

import com.mirachi.dto.audit.AuditLogPageResponseDto;
import com.mirachi.enums.AuditAction;

public interface AuditLogService {

    void logAction(
            String username,
            String role,
            AuditAction action,
            String moduleName,
            String description);

    AuditLogPageResponseDto getLogs(
            int page,
            int size,
            String sortBy,
            String direction);

    AuditLogPageResponseDto searchLogs(
            String keyword,
            int page,
            int size);

    AuditLogPageResponseDto getLogsByUser(
            String username,
            int page,
            int size);

    AuditLogPageResponseDto getLogsByModule(
            String moduleName,
            int page,
            int size);
}