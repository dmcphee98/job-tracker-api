package com.deanmcphee.jobtracker.dto;

import com.deanmcphee.jobtracker.model.JobApplication;
import com.deanmcphee.jobtracker.model.ApplicationStatus;

/**
 * Data Transfer Object for performing partial updates on {@link JobApplication} entities.
 * Used in PATCH endpoints to update one or more fields of a job application.
 * Fields that are null will not be changed in the existing entity.
 */
public record JobApplicationPatchDto(
    String company,
    String role,
    ApplicationStatus status
) {}