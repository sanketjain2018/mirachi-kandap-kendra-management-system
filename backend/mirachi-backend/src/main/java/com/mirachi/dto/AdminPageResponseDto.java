package com.mirachi.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminPageResponseDto {

    private List<AdminResponseDto> admins;

    private int currentPage;

    private int totalPages;

    private long totalElements;

    private boolean hasNext;

    private boolean hasPrevious;
}