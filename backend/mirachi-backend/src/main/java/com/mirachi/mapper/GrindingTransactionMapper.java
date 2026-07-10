package com.mirachi.mapper;

import org.springframework.stereotype.Component;

import com.mirachi.dto.GrindingTransactionResponseDto;
import com.mirachi.entity.GrindingTransaction;

@Component
public class GrindingTransactionMapper {
	 public GrindingTransactionResponseDto mapToResponse(
	            GrindingTransaction transaction) {

	        return GrindingTransactionResponseDto.builder()
	                .id(transaction.getId())
	                .customerName(
	                        transaction.getCustomer().getCustomerName())
	                .itemName(
	                        transaction.getRateMaster().getItemName())
	                .weightInKg(transaction.getWeightInKg())
	                .ratePerKg(transaction.getRatePerKg())
	                .totalAmount(transaction.getTotalAmount())
	                .transactionDate(transaction.getTransactionDate())
	                .status(transaction.getStatus().name())
	                .remarks(transaction.getRemarks())
	                .build();
	    }
}
