package com.mirachi.service;


import com.mirachi.dto.AdminPageResponseDto;
import com.mirachi.dto.AdminResponseDto;
import com.mirachi.dto.CreateAdminRequestDto;

public interface SuperAdminService {
    
    AdminResponseDto createAdmin(CreateAdminRequestDto request);
    
    
    void enableAdmin(Long id);
    
    void disableAdmin(Long id);
    
    void deleteAdmin(Long id);
    
    void unlockAdmin(Long id);
    
    AdminPageResponseDto getAdmins(
            int page,
            int size,
            String sortBy,
            String direction);

    AdminPageResponseDto searchAdmins(
            String keyword,
            int page,
            int size,
            String sortBy,
            String direction);
    
}
