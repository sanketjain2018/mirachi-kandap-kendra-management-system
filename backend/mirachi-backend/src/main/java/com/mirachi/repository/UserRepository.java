package com.mirachi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mirachi.entity.User;
import com.mirachi.enums.Role;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<User> findByRole(
            Role role,
            Pageable pageable);

    Page<User>
    findByRoleAndFullNameContainingIgnoreCase(
            Role role,
            String fullName,
            Pageable pageable);

    Page<User>
    findByRoleAndEmailContainingIgnoreCase(
            Role role,
            String email,
            Pageable pageable);

    Page<User>
    findByRoleAndFullNameContainingIgnoreCaseOrRoleAndEmailContainingIgnoreCase(
            Role role1,
            String fullName,
            Role role2,
            String email,
            Pageable pageable);
}