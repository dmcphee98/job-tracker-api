package com.deanmcphee.jobtracker.controller;

import com.deanmcphee.jobtracker.model.JobApplication;
import com.deanmcphee.jobtracker.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing job applications.
 * Provides endpoints to create, retrieve, and filter job applications.
 */
@RestController
@RequestMapping("/api/job-applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService service;

    /**
     * Get job applications by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getJobApplicationById(@PathVariable Long id) {
        // Wrap the Optional result from the service into a ResponseEntity.
        // Otherwise, if the JobApplication doesn’t exist, we have to return null,
        // and Spring will convert this to HTTP 200 with empty body, which is not RESTful.
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new job application
     */
    @PostMapping
    public JobApplication createJobApplication(@RequestBody JobApplication job) {
        return service.save(job);
    }

    /**
     * Delete an existing job application
     */
    @DeleteMapping("/{id}")
    public void deleteJobApplication(@PathVariable Long id) {
        service.deleteById(id);
    }

}