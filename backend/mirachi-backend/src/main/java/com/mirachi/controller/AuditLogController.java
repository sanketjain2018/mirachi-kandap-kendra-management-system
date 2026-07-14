package com.mirachi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.audit.AuditLogPageResponseDto;
import com.mirachi.service.AuditLogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    @GetMapping
    public ApiResponse<AuditLogPageResponseDto>
    getLogs(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "actionTime")
            String sortBy,

            @RequestParam(defaultValue = "desc")
            String direction) {

        return new ApiResponse<>(
                true,
                "Audit logs fetched successfully",
                auditLogService.getLogs(
                        page,
                        size,
                        sortBy,
                        direction));
    }

    @GetMapping("/search")
    public ApiResponse<AuditLogPageResponseDto>
    searchLogs(
            @RequestParam String keyword,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        return new ApiResponse<>(
                true,
                "Audit logs fetched successfully",
                auditLogService.searchLogs(
                        keyword,
                        page,
                        size));
    }

    @GetMapping("/user/{username}")
    public ApiResponse<AuditLogPageResponseDto>
    getByUser(
            @PathVariable String username,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        return new ApiResponse<>(
                true,
                "User logs fetched successfully",
                auditLogService.getLogsByUser(
                        username,
                        page,
                        size));
    }

    @GetMapping("/module/{moduleName}")
    public ApiResponse<AuditLogPageResponseDto>
    getByModule(
            @PathVariable String moduleName,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        return new ApiResponse<>(
                true,
                "Module logs fetched successfully",
                auditLogService.getLogsByModule(
                        moduleName,
                        page,
                        size));
    }
}