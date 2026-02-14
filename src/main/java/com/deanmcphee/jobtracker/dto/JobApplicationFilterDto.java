package com.deanmcphee.jobtracker.dto;

import com.deanmcphee.jobtracker.model.JobApplication;
import com.deanmcphee.jobtracker.model.ApplicationStatus;

import java.util.List;

/**
 * Data Transfer Object for filtering {@link JobApplication} entities.
 * Used in GET endpoints that support querying or searching job applications.
 * Each field is optional: if a field is null, it is ignored during filtering.
 */
public record JobApplicationFilterDto(
    List<ApplicationStatus> status
) {}