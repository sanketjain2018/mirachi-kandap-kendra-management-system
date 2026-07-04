package com.mirachi.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mirachi.dto.RegisterRequestDto;
import com.mirachi.entity.User;
import com.mirachi.enums.Role;
import com.mirachi.repository.UserRepository;
import com.mirachi.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public String registerUser(RegisterRequestDto request) {
		User user = User.builder()
				.fullName(request.getFullName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.SUPER_ADMIN)
				.build();
		userRepository.save(user);
		return "User Registered Successfully";
	}

}
