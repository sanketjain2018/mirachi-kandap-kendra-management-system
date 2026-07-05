package com.mirachi.service.impl;

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

	    User user = userRepository.findByEmail(request.getEmail())
	            .orElseThrow(() ->
	                    new UserNotFoundException("User Not Found"));

	    boolean isPasswordValid =
	            passwordEncoder.matches(
	                    request.getPassword(),
	                    user.getPassword());

	    if (!isPasswordValid) {
	        throw new InvalidCredentialsException(
	                "Invalid Credentials");
	    }

	    String token = jwtUtil.generateToken(user.getEmail());

	    LoginResponseDto loginData =
	            new LoginResponseDto(token);

	    return new ApiResponse<>(
	            true,
	            "Login Successful",
	            loginData);
	}

}
