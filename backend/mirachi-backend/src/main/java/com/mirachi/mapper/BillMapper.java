package com.mirachi.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mirachi.dto.BillItemResponseDto;
import com.mirachi.dto.BillResponseDto;
import com.mirachi.entity.Bill;

@Component
public class BillMapper {

	public BillResponseDto mapToResponse(Bill bill) {

		List<BillItemResponseDto> items = bill.getBillItems().stream()
				.map(item -> BillItemResponseDto.builder().itemName(item.getItemName()).weightInKg(item.getWeightInKg())
						.ratePerKg(item.getRatePerKg()).amount(item.getAmount()).build())
				.toList();

		return BillResponseDto.builder().id(bill.getId()).billNumber(bill.getBillNumber())
				.customerName(bill.getCustomer().getCustomerName()).billDate(bill.getBillDate())
				.totalAmount(bill.getTotalAmount()).paymentStatus(bill.getPaymentStatus().name()).notes(bill.getNotes())
				.items(items).build();
	}
}