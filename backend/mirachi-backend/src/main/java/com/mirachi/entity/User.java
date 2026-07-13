package com.mirachi.entity;

import java.time.LocalDateTime;

import com.mirachi.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(nullable = false)
	private Boolean enabled = true;

	@Column(nullable = false)
	private Boolean accountLocked = false;

	@Column(nullable = false)
	private Integer failedAttempts = 0;

	private LocalDateTime lastLoginAt;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	@PrePersist
	public void prePersist() {

	    createdAt = LocalDateTime.now();
	    updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {

	    updatedAt = LocalDateTime.now();
	}
}
