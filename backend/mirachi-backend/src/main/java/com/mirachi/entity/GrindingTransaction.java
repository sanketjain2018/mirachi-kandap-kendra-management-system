package com.mirachi.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.mirachi.enums.TransactionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grinding_transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrindingTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id",
				nullable = false)
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rate_master_id",
				nullable = false)
	private RateMaster rateMaster;
	
	@Column(nullable = false)
	private Double weightInKg;
	
	@Column(nullable = false)
	private BigDecimal ratePerKg;
	
	@Column(nullable = false)
	private BigDecimal totalAmount;
	
	private String remarks;
	
	private LocalDate transactionDate;
	
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.transactionDate = LocalDate.now();
		this.status = TransactionStatus.COMPLETED;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
	
}





