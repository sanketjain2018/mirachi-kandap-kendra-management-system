package com.mirachi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.RateMaster;

public interface RateMasterRepository extends JpaRepository<RateMaster, Long> {
	
	Optional<RateMaster> findByItemNameIgnoreCase(String itemName);
	
	boolean existsByItemNameIgnoreCase(String itemName);
}
