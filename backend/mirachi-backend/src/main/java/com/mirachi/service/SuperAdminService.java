package com.mirachi.service;

import java.util.List;

import com.mirachi.dto.AdminResponseDto;
import com.mirachi.dto.CreateAdminRequestDto;

public interface SuperAdminService {
    
    AdminResponseDto createAdmin(CreateAdminRequestDto request);
    
    List<AdminResponseDto> getAllAdmins();
    
    void enableAdmin(Long id);
    
    void disableAdmin(Long id);
    
    void deleteAdmin(Long id);
    
    void unlockAdmin(Long id);
    
}
