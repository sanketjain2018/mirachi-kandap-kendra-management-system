package com.mirachi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateMasterResponseDto {
	
	private Long id;
	private String itemName;
	private BigDecimal ratePerKg;
	private String description;
	private boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
