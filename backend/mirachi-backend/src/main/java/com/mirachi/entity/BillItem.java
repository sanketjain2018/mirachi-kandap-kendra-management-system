package com.mirachi.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bill_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Many bill items belong to one bill
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;


    @Column(nullable = false)
    private String itemName;


    @Column(nullable = false)
    private Double weightInKg;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ratePerKg;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
}