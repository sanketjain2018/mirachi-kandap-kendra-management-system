package com.mirachi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.BusinessSetting;

public interface BusinessSettingRepository
        extends JpaRepository<BusinessSetting, Long> {

    Optional<BusinessSetting> findTopByOrderByIdAsc();

    boolean existsByBusinessNameIgnoreCase(
            String businessName);
}