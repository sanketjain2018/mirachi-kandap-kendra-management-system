package com.mirachi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirachi.entity.BusinessSetting;

@Repository
public interface BusinessSettingRepository
        extends JpaRepository<BusinessSetting, Long> {

    /**
     * Returns the single business settings record.
     */
    Optional<BusinessSetting> findTopByOrderByIdAsc();

    /**
     * Checks whether a business profile already exists.
     */
    boolean existsByBusinessNameIgnoreCase(
            String businessName);
}