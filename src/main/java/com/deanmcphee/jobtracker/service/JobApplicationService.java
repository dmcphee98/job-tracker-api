package com.deanmcphee.jobtracker.service;

import com.deanmcphee.jobtracker.model.JobApplication;
import com.deanmcphee.jobtracker.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service for managing {@link JobApplication} entities.
 */
@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public Optional<JobApplication> getById(Long id) {
        return repository.findById(id);
    }

    public JobApplication save(JobApplication job) {
        return repository.save(job);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}