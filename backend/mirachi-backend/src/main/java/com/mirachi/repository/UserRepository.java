package com.mirachi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.User;
import com.mirachi.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByEmail(String Email);
	
    List<User> findByRole(Role role);

    Page<User> findByRole(
	        Role role,
	        Pageable pageable);

    boolean existsByEmail(String email);
}
