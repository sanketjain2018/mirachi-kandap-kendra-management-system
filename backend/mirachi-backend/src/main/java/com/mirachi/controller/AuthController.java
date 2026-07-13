package com.mirachi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.LoginRequestDto;
import com.mirachi.dto.LoginResponseDto;
import com.mirachi.dto.RegisterRequestDto;
import com.mirachi.security.JwtUtil;
import com.mirachi.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
	private final JwtUtil jwtUtil;

	@PostMapping("/register")
	public ApiResponse<Object> registerUser(
	        RegisterRequestDto request) {
		return new ApiResponse<>(
		        true,
		        "User Registered Successfully",
		        null);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponseDto>> login(
	        @RequestBody LoginRequestDto request) {

	    return ResponseEntity.ok(
	            userService.loginUser(request));
	}
	
	@GetMapping("/token")
	public String token() {

	    return jwtUtil.generateToken(
	            "sanket@gmail.com",  "SUPER_ADMIN");
	}
	
	@GetMapping("/hello")
	public String hello() {
	    return "Authenticated User";
	}
}
	