package com.deanmcphee.jobtracker.dto;

import com.deanmcphee.jobtracker.model.ApplicationStatus;
import com.deanmcphee.jobtracker.model.JobApplication;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object for sending {@link JobApplication} data to clients without exposing the internal entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDto {

    private Long id;
    private String company;
    private String role;
    private ApplicationStatus status;

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