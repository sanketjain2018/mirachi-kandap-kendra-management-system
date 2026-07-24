package com.mirachi.service;

import com.mirachi.dto.BusinessSettingRequestDto;
import com.mirachi.dto.BusinessSettingResponseDto;

public interface BusinessSettingService {

    BusinessSettingResponseDto createBusinessSetting(
            BusinessSettingRequestDto request);

    BusinessSettingResponseDto getBusinessSetting();

    BusinessSettingResponseDto updateBusinessSetting(
            BusinessSettingRequestDto request);
}