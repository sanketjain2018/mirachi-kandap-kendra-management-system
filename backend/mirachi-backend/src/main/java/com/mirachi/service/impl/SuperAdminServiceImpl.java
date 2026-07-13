package com.mirachi.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
public class SuperAdminServiceImpl implements SuperAdminService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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

        return mapToResponse(saved);
    }

    @Override
    public List<AdminResponseDto>
    getAllAdmins() {

        return userRepository
                .findByRole(Role.ADMIN)
                .stream()
                .map(this::mapToResponse)
                .toList();
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
    }

    @Override
    public void deleteAdmin(Long id) {

        User admin = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new AdminNotFoundException(
                                "Admin not found"));

        userRepository.delete(admin);
    }

    private AdminResponseDto
    mapToResponse(User user) {

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
    
    @Override
    public void unlockAdmin(Long id) {

        User admin =
                userRepository.findById(id)
                        .orElseThrow(() ->
                                new AdminNotFoundException(
                                        "Admin not found"));

        admin.setAccountLocked(false);

        admin.setFailedAttempts(0);

        userRepository.save(admin);
    }
    
}