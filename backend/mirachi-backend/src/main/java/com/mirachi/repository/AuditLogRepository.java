package com.mirachi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.AuditLog;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {

    Page<AuditLog> findByModuleNameContainingIgnoreCase(
            String moduleName,
            Pageable pageable);

    Page<AuditLog> findByUsernameContainingIgnoreCase(
            String username,
            Pageable pageable);

    Page<AuditLog>
    findByDescriptionContainingIgnoreCase(
            String keyword,
            Pageable pageable);

    List<AuditLog> findTop10ByOrderByActionTimeDesc();
}