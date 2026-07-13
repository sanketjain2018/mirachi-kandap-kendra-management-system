package com.mirachi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.AdminResponseDto;
import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.CreateAdminRequestDto;
import com.mirachi.service.SuperAdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/super-admin")
@RequiredArgsConstructor
public class SuperAdminController {

    private final SuperAdminService service;

    @PostMapping("/admins")
    public ApiResponse<AdminResponseDto>
    createAdmin(
            @RequestBody
            CreateAdminRequestDto request) {

        return new ApiResponse<>(
                true,
                "Admin created successfully",
                service.createAdmin(request));
    }

    @GetMapping("/admins")
    public ApiResponse<List<AdminResponseDto>>
    getAllAdmins() {

        return new ApiResponse<>(
                true,
                "Admins fetched successfully",
                service.getAllAdmins());
    }

    @PutMapping("/admins/{id}/enable")
    public ApiResponse<String>
    enableAdmin(
            @PathVariable Long id) {

        service.enableAdmin(id);

        return new ApiResponse<>(
                true,
                "Admin enabled successfully",
                null);
    }

    @PutMapping("/admins/{id}/disable")
    public ApiResponse<String>
    disableAdmin(
            @PathVariable Long id) {

        service.disableAdmin(id);

        return new ApiResponse<>(
                true,
                "Admin disabled successfully",
                null);
    }

    @DeleteMapping("/admins/{id}")
    public ApiResponse<String>
    deleteAdmin(
            @PathVariable Long id) {

        service.deleteAdmin(id);

        return new ApiResponse<>(
                true,
                "Admin deleted successfully",
                null);
    }
    
    @PutMapping("/admins/{id}/unlock")
    public ApiResponse<String> unlockAdmin(
            @PathVariable Long id) {

        service.unlockAdmin(id);

        return new ApiResponse<>(
                true,
                "Admin unlocked successfully",
                null);
    }
}