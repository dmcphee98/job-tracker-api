package com.deanmcphee.jobtracker.dto;

import com.deanmcphee.jobtracker.model.ApplicationStatus;
import com.deanmcphee.jobtracker.model.JobApplication;

/**
 * Data Transfer Object for creating {@link JobApplication} entities.
 */
public record JobApplicationCreateDto (
    String company,
    String role,
    ApplicationStatus status
) {}