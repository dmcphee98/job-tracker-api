package com.deanmcphee.jobtracker.dto;

import com.deanmcphee.jobtracker.model.ApplicationStatus;
import com.deanmcphee.jobtracker.model.JobApplication;

/**
 * Data Transfer Object for sending {@link JobApplication} data to clients without exposing the internal entity.
 */
public record JobApplicationDto(
    Long id,
    String company,
    String role,
    ApplicationStatus status
) {
    /**
     * Factory method used in a Controller to create a {@link JobApplicationDto} from a {@link JobApplication}.
     * @param entity the {@link JobApplication} entity
     * @return a {@link JobApplicationDto} with the corresponding data
     */
    public static JobApplicationDto fromEntity(JobApplication entity) {
        return new JobApplicationDto(
                entity.getId(),
                entity.getCompany(),
                entity.getRole(),
                entity.getStatus()
        );
    }
}