package com.mirachi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mirachi.dto.BillItemRequestDto;
import com.mirachi.dto.BillRequestDto;
import com.mirachi.dto.BillResponseDto;
import com.mirachi.entity.Bill;
import com.mirachi.entity.BillItem;
import com.mirachi.entity.Customer;
import com.mirachi.enums.PaymentStatus;
import com.mirachi.exception.BillNotFoundException;
import com.mirachi.exception.CustomerNotFoundException;
import com.mirachi.mapper.BillMapper;
import com.mirachi.repository.BillRepository;
import com.mirachi.repository.CustomerRepository;
import com.mirachi.service.BillService;
import com.mirachi.util.BillNumberGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillServiceImpl
        implements BillService {

    private final BillRepository billRepository;

    private final CustomerRepository customerRepository;

    private final BillMapper billMapper;

    private final BillNumberGenerator
            billNumberGenerator;

    @Override
    public BillResponseDto createBill(
            BillRequestDto request) {

        Customer customer =
                customerRepository.findById(
                        request.getCustomerId())
                        .orElseThrow(
                                () ->
                                        new CustomerNotFoundException(
                                                "Customer not found"));

        Bill bill = new Bill();

        bill.setBillNumber(
                billNumberGenerator
                        .generateBillNumber());

        bill.setCustomer(customer);

        bill.setPaymentStatus(
                request.getPaymentStatus());

        bill.setNotes(
                request.getNotes());

        List<BillItem> billItems =
                request.getItems()
                        .stream()
                        .map(item ->
                                createBillItem(
                                        bill,
                                        item))
                        .toList();

        bill.setBillItems(
                billItems);

        BigDecimal totalAmount =
                billItems.stream()
                        .map(
                                BillItem::getAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add);

        bill.setTotalAmount(
                totalAmount);

        Bill savedBill =
                billRepository.save(
                        bill);

        return billMapper
                .mapToResponse(
                        savedBill);
    }
    
    
    private BillItem createBillItem(
            Bill bill,
            BillItemRequestDto item) {

        BigDecimal amount =
                item.getRatePerKg()
                        .multiply(
                                BigDecimal.valueOf(
                                        item.getWeightInKg()));

        return BillItem.builder()
                .bill(bill)
                .itemName(
                        item.getItemName())
                .weightInKg(
                        item.getWeightInKg())
                .ratePerKg(
                        item.getRatePerKg())
                .amount(amount)
                .build();
    }


    @Override
    public List<BillResponseDto>
    getAllBills() {

        return billRepository.findAll()
                .stream()
                .map(
                        billMapper::mapToResponse)
                .toList();
    }


	@Override
	public BillResponseDto getBillById(
	        Long billId) {

	    Bill bill =
	            billRepository.findById(
	                    billId)
	                    .orElseThrow(
	                            () ->
	                                    new BillNotFoundException(
	                                            "Bill not found"));

	    return billMapper
	            .mapToResponse(
	                    bill);
	}


	@Override
	public BillResponseDto getBillByBillNumber(String billNumber) {
		 Bill bill =
		            billRepository.findByBillNumber(
		                    billNumber)
		                    .orElseThrow(
		                            () ->
		                                    new BillNotFoundException(
		                                            "Bill not found with number: "
		                                                    + billNumber));

		    return billMapper.mapToResponse(
		            bill);
		
	}


	@Override
	public List<BillResponseDto> searchBillsByCustomerName(String customerName) {
		return billRepository
	            .searchByCustomerName(
	                    customerName)
	            .stream()
	            .map(
	                    billMapper::mapToResponse)
	            .toList();
	}


	@Override
	public List<BillResponseDto> searchBillsByStatus(PaymentStatus status) {
		 return billRepository
		            .findByPaymentStatus(
		                    status)
		            .stream()
		            .map(
		                    billMapper::mapToResponse)
		            .toList();
	}


	@Override
	public List<BillResponseDto> searchBillsByDateRange(LocalDate startDate, LocalDate endDate) {
		 if (startDate.isAfter(
		            endDate)) {

		        throw new IllegalArgumentException(
		                "Start date cannot be after end date");
		    }

		    return billRepository
		            .findByBillDateBetween(
		                    startDate,
		                    endDate)
		            .stream()
		            .map(
		                    billMapper::mapToResponse)
		            .toList();
	}
    
	
	@Override
	public List<BillResponseDto>
	getCustomerBillHistory(
	        Long customerId) {

	    if (!customerRepository.existsById(
	            customerId)) {

	        throw new CustomerNotFoundException(
	                "Customer not found");
	    }

	    return billRepository
	            .findByCustomerId(
	                    customerId)
	            .stream()
	            .map(
	                    billMapper::mapToResponse)
	            .toList();
	}
}