package com.deanmcphee.jobtracker.repository;

import com.deanmcphee.jobtracker.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link JobApplication} entities.
 * Extends {@link JpaRepository} to provide standard CRUD operations.
 */
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>, JpaSpecificationExecutor<JobApplication> {

    /**
     * Finds a {@link JobApplication} by its ID and associated user ID.
     *
     * @param id the ID of the job application
     * @param userId the ID of the user
     * @return an {@link Optional} containing the found entity, or empty if none exists
     */
    Optional<JobApplication> findByIdAndUserId(Long id, String userId);

    /**
     * Checks whether a {@link JobApplication} exists for the given ID and user ID.
     *
     * @param id the ID of the job application
     * @param userId the ID of the user
     * @return true if a job application exists with the given ID for the user, false otherwise
     */
    boolean existsByIdAndUserId(Long id, String userId);


    /**
     * Deletes the {@link JobApplication} with the given ID and user ID
     *
     * @param id the ID of the job application
     * @param userId the ID of the user
     */
    void deleteByIdAndUserId(Long id, String userId);

}