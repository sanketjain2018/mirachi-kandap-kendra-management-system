package com.mirachi.service.impl;

import com.mirachi.enums.AuditAction;
import com.mirachi.service.AuditLogService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.LoginRequestDto;
import com.mirachi.dto.LoginResponseDto;
import com.mirachi.dto.RegisterRequestDto;
import com.mirachi.entity.User;
import com.mirachi.enums.Role;
import com.mirachi.exception.InvalidCredentialsException;
import com.mirachi.exception.UserNotFoundException;
import com.mirachi.repository.UserRepository;
import com.mirachi.security.JwtUtil;
import com.mirachi.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final AuditLogService auditLogService;

	@Override
	public String registerUser(RegisterRequestDto request) {
		User user = User.builder().fullName(request.getFullName()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).role(Role.ADMIN).build();
		userRepository.save(user);
		return "User Registered Successfully";
	}

	@Override
	public ApiResponse<LoginResponseDto> loginUser(
	        LoginRequestDto request) {

	    User user = userRepository
	            .findByEmail(request.getEmail())
	            .orElseThrow(() ->
	                    new UserNotFoundException(
	                            "User Not Found"));

	    // Account Disabled
	    if (!user.getEnabled()) {

	        throw new InvalidCredentialsException(
	                "Account is disabled. Contact Super Admin.");
	    }

	    // Account Locked
	    if (user.getAccountLocked()) {

	        throw new InvalidCredentialsException(
	                "Account is locked. Contact Super Admin.");
	    }

	    boolean isPasswordValid =
	            passwordEncoder.matches(
	                    request.getPassword(),
	                    user.getPassword());

	    if (!isPasswordValid) {

	        Integer attempts =
	                user.getFailedAttempts() + 1;

	        user.setFailedAttempts(attempts);

	        // Lock after 5 attempts
	        if (attempts >= 5) {

	            user.setAccountLocked(true);
	        }

	        userRepository.save(user);

	        throw new InvalidCredentialsException(
	                "Invalid Credentials");
	    }

	    // Successful Login

	    user.setFailedAttempts(0);

	    user.setLastLoginAt(
	            java.time.LocalDateTime.now());

	    userRepository.save(user);
	    
	    auditLogService.logAction(
		        user.getEmail(),
		        user.getRole().name(),
		        AuditAction.LOGIN,
		        "Authentication",
		        "User logged in successfully");

	    String token =
		        jwtUtil.generateToken(
		                user.getEmail(),
		                user.getRole().name());

	    LoginResponseDto loginData =
	            new LoginResponseDto(token);

	    return new ApiResponse<>(
	            true,
	            "Login Successful",
	            loginData);
	}

}
