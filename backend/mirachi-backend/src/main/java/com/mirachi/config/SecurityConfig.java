package com.mirachi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mirachi.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

                    // Public APIs
                    .requestMatchers(
                            "/api/auth/**")
                    .permitAll()

                    // Super Admin APIs
                    .requestMatchers(
                            "/api/super-admin/**")
                    .hasRole("SUPER_ADMIN")

                    // Admin + Super Admin APIs
                    .requestMatchers(
                            "/api/customers/**",
                            "/api/rates/**",
                            "/api/transactions/**",
                            "/api/dashboard/**",
                            "/api/bills/**",
                            "/api/expenses/**")
                    .hasAnyRole(
                            "ADMIN",
                            "SUPER_ADMIN")

                    // Any other API
                    .anyRequest()
                    .authenticated())

            .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}