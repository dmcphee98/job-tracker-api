package com.deanmcphee.jobtracker.service;

import com.deanmcphee.jobtracker.dto.JobApplicationCreateDto;
import com.deanmcphee.jobtracker.dto.JobApplicationDto;
import com.deanmcphee.jobtracker.dto.JobApplicationPatchDto;
import com.deanmcphee.jobtracker.model.JobApplication;
import com.deanmcphee.jobtracker.repository.JobApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for managing {@link JobApplication} entities.
 */
@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repository;

    /**
     * Retrieves a {@link JobApplication} by its ID.
     * @param id the ID of the {@link JobApplication} to retrieve
     * @return the {@link JobApplicationDto} entity
     * @throws EntityNotFoundException if no {@link JobApplication} with the given ID exists
     */
    public JobApplicationDto getById(Long id) {
        JobApplication found = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Job application not found with ID " + id));
        return JobApplicationDto.fromEntity(found);
    }

    /**
     * Creates a new {@link JobApplication} from the provided creation request.
     * <p>
     * This method maps the incoming {@link JobApplicationCreateDto} to a
     * {@link JobApplication} entity, persists it using the repository,
     * and returns a {@link JobApplicationDto} representing the saved entity.
     *
     * @param request the data required to create a new {@link JobApplication}
     * @return a {@link JobApplicationDto} representing the newly created {@link JobApplication}
     */
    public JobApplicationDto create(JobApplicationCreateDto request) {
        JobApplication entity = new JobApplication();
        entity.setCompany(request.company());
        entity.setRole(request.role());
        entity.setStatus(request.status());

        JobApplication saved = repository.save(entity);
        return JobApplicationDto.fromEntity(saved);
    }

    /**
     * Fully updates an existing {@link JobApplication} by replacing its data with the values provided in the request.
     * <p>
     * All fields in the DTO are required. Missing fields will not be filled automatically.
     *
     * @param id the ID of the {@link JobApplication} to update
     * @param request the data required to update the {@link JobApplication}
     * @return a {@link JobApplicationDto} representing the updated {@link JobApplication}
     * @throws EntityNotFoundException if no {@link JobApplication} with the given ID exists
     */
    public JobApplicationDto update(Long id, JobApplicationCreateDto request) {
        JobApplication entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Job application not found with ID " + id));

        entity.setCompany(request.company());
        entity.setRole(request.role());
        entity.setStatus(request.status());

        JobApplication saved = repository.save(entity);
        return JobApplicationDto.fromEntity(saved);
    }

    /**
     * Applies partial updates to an existing {@link JobApplication}.
     * <p>
     * Only non-null fields in the provided {@link JobApplicationPatchDto} are applied to the existing entity.
     *
     * @param id the ID of the {@link JobApplication} to update
     * @param request the data with which to partially update the {@link JobApplication}
     * @return a {@link JobApplicationDto} representing the updated {@link JobApplication}
     * @throws EntityNotFoundException if no {@link JobApplication} with the given ID exists
     */
    public JobApplicationDto patch(Long id, JobApplicationPatchDto request) {
        JobApplication entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Job application not found with ID " + id));

        if (request.company() != null) entity.setCompany(request.company());
        if (request.role() != null) entity.setRole(request.role());
        if (request.status() != null) entity.setStatus(request.status());

        JobApplication saved = repository.save(entity);
        return JobApplicationDto.fromEntity(saved);
    }

    /**
     * Deletes a {@link JobApplication} by its ID.
     *
     * @param id the ID of the {@link JobApplication} to delete
     * @throws EntityNotFoundException if no {@link JobApplication} with the given ID exists
     */
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Job application not found with ID " + id);
        }
        repository.deleteById(id);
    }

}