package com.mirachi.service;

import com.mirachi.dto.RegisterRequestDto;

public interface UserService {
	
	String registerUser(RegisterRequestDto request);
}
