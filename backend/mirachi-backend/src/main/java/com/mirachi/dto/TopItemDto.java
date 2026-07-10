package com.mirachi.dto;

import java.math.BigDecimal;

public record TopItemDto(
		
		String itemName,
		Long totalOrders,
		BigDecimal totalRevenue) {

}
