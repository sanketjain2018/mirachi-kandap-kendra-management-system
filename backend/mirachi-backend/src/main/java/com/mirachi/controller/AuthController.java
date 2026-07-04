package com.mirachi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirachi.dto.RegisterRequestDto;
import com.mirachi.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequestDto request) {
		return userService.registerUser(request);
	}
}
