package com.deanmcphee.jobtracker.service;

import com.deanmcphee.jobtracker.model.JobApplication;
import com.deanmcphee.jobtracker.repository.JobApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
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
     * Retrieves a {@link JobApplication} by its id.
     * @param id the id of the {@link JobApplication} to retrieve
     * @return the {@link JobApplication} entity
     * @throws EntityNotFoundException if no {@link JobApplication} with the given ID exists
     */
    public JobApplication getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Job application not found with id " + id));    }

    /**
     * Saves a new or updated JobApplication entity.
     * @param job the {@link JobApplication} to save
     * @return the saved {@link JobApplication} entity
     * @throws DataIntegrityViolationException if the save violates database constraints
     */
    public JobApplication save(JobApplication job) {
        return repository.save(job);
    }

    /**
     * Deletes a {@link JobApplication} by its ID.
     *
     * @param id the ID of the {@link JobApplication} to delete
     * @throws EntityNotFoundException if no {@link JobApplication} with the given ID exists
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}