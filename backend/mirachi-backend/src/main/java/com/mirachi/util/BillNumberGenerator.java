package com.mirachi.util;

import java.time.Year;

import org.springframework.stereotype.Component;

import com.mirachi.repository.BillRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BillNumberGenerator {

    private final BillRepository billRepository;

    public String generateBillNumber() {

        long nextSequence =
                billRepository.count() + 1;

        return String.format(
                "MKK-%d-%05d",
                Year.now().getValue(),
                nextSequence);
    }
}