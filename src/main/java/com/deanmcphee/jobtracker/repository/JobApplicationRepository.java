package com.deanmcphee.jobtracker.repository;

import com.deanmcphee.jobtracker.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link JobApplication} entities.
 * Extends {@link JpaRepository} to provide standard CRUD operations.
 */
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

}