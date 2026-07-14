package com.mirachi.service.impl;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.mirachi.dto.audit.AuditLogPageResponseDto;
import com.mirachi.dto.audit.AuditLogResponseDto;
import com.mirachi.entity.AuditLog;
import com.mirachi.enums.AuditAction;
import com.mirachi.mapper.AuditLogMapper;
import com.mirachi.repository.AuditLogRepository;
import com.mirachi.service.AuditLogService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl
        implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    private final AuditLogMapper auditLogMapper;

    @Override
    public void logAction(
            String username,
            String role,
            AuditAction action,
            String moduleName,
            String description) {

        AuditLog auditLog =
                AuditLog.builder()
                        .username(username)
                        .role(role)
                        .action(action)
                        .moduleName(moduleName)
                        .description(description)
                        .build();

        auditLogRepository.save(auditLog);
    }

    @Override
    public AuditLogPageResponseDto getLogs(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        Page<AuditLog> auditPage =
                auditLogRepository.findAll(pageable);

        return buildResponse(auditPage);
    }

    @Override
    public AuditLogPageResponseDto searchLogs(
            String keyword,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<AuditLog> auditPage =
                auditLogRepository
                        .findByDescriptionContainingIgnoreCase(
                                keyword,
                                pageable);

        return buildResponse(auditPage);
    }

    @Override
    public AuditLogPageResponseDto getLogsByUser(
            String username,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<AuditLog> auditPage =
                auditLogRepository
                        .findByUsernameContainingIgnoreCase(
                                username,
                                pageable);

        return buildResponse(auditPage);
    }

    @Override
    public AuditLogPageResponseDto getLogsByModule(
            String moduleName,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<AuditLog> auditPage =
                auditLogRepository
                        .findByModuleNameContainingIgnoreCase(
                                moduleName,
                                pageable);

        return buildResponse(auditPage);
    }

    private AuditLogPageResponseDto buildResponse(
            Page<AuditLog> page) {

        List<AuditLogResponseDto> logs =
                page.getContent()
                        .stream()
                        .map(auditLogMapper::toResponse)
                        .toList();

        return AuditLogPageResponseDto.builder()
                .logs(logs)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();
    }
}