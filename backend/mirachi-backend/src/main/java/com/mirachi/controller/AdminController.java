package com.mirachi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@GetMapping("/dashboard")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public String dashboard() {
		return "Welcome Super Admin";
	}
	
	
	@GetMapping("/profile")
	@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
	public String profile() {
		return "Admin Profile";
	}
}
