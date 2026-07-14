package com.mirachi.entity;

import java.time.LocalDateTime;

import com.mirachi.enums.AuditAction;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditAction action;

    @Column(nullable = false)
    private String moduleName;

    @Column(nullable = false, length = 1000)
    private String description;

    private LocalDateTime actionTime;

    @PrePersist
    public void prePersist() {
        this.actionTime = LocalDateTime.now();
    }
}