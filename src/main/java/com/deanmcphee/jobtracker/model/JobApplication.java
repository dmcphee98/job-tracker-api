package com.deanmcphee.jobtracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents a job application submitted by a user.
 * This entity is mapped to the {@code job_application} table in the database.
 * Each instance corresponds to a single job application with its company, role, and current status.
 * The {@code id} field is auto-generated as the primary key.
 */
@Entity
@Table(name = "job_application")
@Data
@NoArgsConstructor
public class JobApplication {

    // Designate id as primary key. Auto-generate its value.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String userId; // Cognito sub

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Store as String, not integer
    private ApplicationStatus status;
}