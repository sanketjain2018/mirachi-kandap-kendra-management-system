package com.mirachi.service;

import com.mirachi.dto.ApiResponse;
import com.mirachi.dto.LoginRequestDto;
import com.mirachi.dto.LoginResponseDto;
import com.mirachi.dto.RegisterRequestDto;

public interface UserService {

	String registerUser(RegisterRequestDto request);

	ApiResponse<LoginResponseDto> loginUser(LoginRequestDto request);
}
