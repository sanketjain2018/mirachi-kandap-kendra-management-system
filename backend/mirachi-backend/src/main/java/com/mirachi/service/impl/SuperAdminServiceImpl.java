package com.mirachi.service.impl;
import com.mirachi.enums.AuditAction;
import com.mirachi.service.AuditLogService;
import com.mirachi.util.SecurityUtil;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mirachi.dto.AdminPageResponseDto;
import com.mirachi.dto.AdminResponseDto;
import com.mirachi.dto.CreateAdminRequestDto;
import com.mirachi.entity.User;
import com.mirachi.enums.Role;
import com.mirachi.exception.AdminNotFoundException;
import com.mirachi.repository.UserRepository;
import com.mirachi.service.SuperAdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuperAdminServiceImpl
        implements SuperAdminService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    
    private final AuditLogService auditLogService;

    @Override
    public AdminResponseDto createAdmin(
            CreateAdminRequestDto request) {

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new RuntimeException(
                    "Email already exists");
        }

        User admin = User.builder()
                .fullName(
                        request.getFullName())
                .email(
                        request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role(Role.ADMIN)
                .enabled(true)
                .build();

        User saved =
                userRepository.save(admin);
        
        auditLogService.logAction(
                SecurityUtil.getCurrentUsername(),
                SecurityUtil.getCurrentRole(),
                AuditAction.CREATE,
                "Admin Management",
                "Created admin " + saved.getEmail());

        return mapToResponse(saved);
    }

 

    @Override
    public void enableAdmin(Long id) {

        User admin = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new AdminNotFoundException(
                                "Admin not found"));

        admin.setEnabled(true);

        userRepository.save(admin);
        
        auditLogService.logAction(
                SecurityUtil.getCurrentUsername(),
                SecurityUtil.getCurrentRole(),
                AuditAction.ENABLE,
                "Admin Management",
                "Enabled admin " + admin.getEmail());
    }

    @Override
    public void disableAdmin(Long id) {

        User admin = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new AdminNotFoundException(
                                "Admin not found"));

        admin.setEnabled(false);

        userRepository.save(admin);
        
        auditLogService.logAction(
                SecurityUtil.getCurrentUsername(),
                SecurityUtil.getCurrentRole(),
                AuditAction.DISABLE,
                "Admin Management",
                "Disabled admin " + admin.getEmail());
    }

    @Override
    public void deleteAdmin(Long id) {

        User admin = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new AdminNotFoundException(
                                "Admin not found"));

        userRepository.delete(admin);
        
        auditLogService.logAction(
                SecurityUtil.getCurrentUsername(),
                SecurityUtil.getCurrentRole(),
                AuditAction.DELETE,
                "Admin Management",
                "Deleted admin " + admin.getEmail());
    }

    @Override
    public void unlockAdmin(Long id) {

        User admin = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new AdminNotFoundException(
                                "Admin not found"));

        admin.setAccountLocked(false);
        admin.setFailedAttempts(0);

        userRepository.save(admin);
        
        auditLogService.logAction(
                SecurityUtil.getCurrentUsername(),
                SecurityUtil.getCurrentRole(),
                AuditAction.UNLOCK,
                "Admin Management",
                "Unlocked admin " + admin.getEmail());
    }

    @Override
    public AdminPageResponseDto getAdmins(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort =
                direction.equalsIgnoreCase("desc")
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort);

        Page<User> adminPage =
                userRepository.findByRole(
                        Role.ADMIN,
                        pageable);

        return buildAdminPageResponse(
                adminPage);
    }

    @Override
    public AdminPageResponseDto searchAdmins(
            String keyword,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort =
                direction.equalsIgnoreCase("desc")
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort);

        Page<User> adminPage =
                userRepository
                        .findByRoleAndFullNameContainingIgnoreCaseOrRoleAndEmailContainingIgnoreCase(
                                Role.ADMIN,
                                keyword,
                                Role.ADMIN,
                                keyword,
                                pageable);

        return buildAdminPageResponse(
                adminPage);
    }

    private AdminPageResponseDto buildAdminPageResponse(
            Page<User> adminPage) {

        return AdminPageResponseDto.builder()
                .admins(
                        adminPage.getContent()
                                .stream()
                                .map(this::mapToResponse)
                                .toList())
                .currentPage(
                        adminPage.getNumber())
                .totalPages(
                        adminPage.getTotalPages())
                .totalElements(
                        adminPage.getTotalElements())
                .hasNext(
                        adminPage.hasNext())
                .hasPrevious(
                        adminPage.hasPrevious())
                .build();
    }

    private AdminResponseDto mapToResponse(
            User user) {

        return AdminResponseDto.builder()
                .id(user.getId())
                .fullName(
                        user.getFullName())
                .email(
                        user.getEmail())
                .enabled(
                        user.getEnabled())
                .lastLoginAt(
                        user.getLastLoginAt())
                .build();
    }
}