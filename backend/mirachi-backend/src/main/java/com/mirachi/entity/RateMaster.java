package com.mirachi.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rate_master")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "item_name", nullable = false, unique = true)
	private String itemName;
	
	@Column(name = "rate_per_kg", nullable = false)
	private BigDecimal ratePerKg;
	
	
	@Column(length = 500)
	private String description;
	
	
	private boolean active;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.active = true;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
}
