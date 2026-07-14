package com.mirachi.dto.audit;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditLogPageResponseDto {

    private List<AuditLogResponseDto> logs;

    private int currentPage;

    private int totalPages;

    private long totalElements;

    private boolean hasNext;

    private boolean hasPrevious;
}