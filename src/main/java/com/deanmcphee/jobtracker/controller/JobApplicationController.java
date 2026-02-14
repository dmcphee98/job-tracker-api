package com.deanmcphee.jobtracker.controller;

import com.deanmcphee.jobtracker.model.JobApplication;
import com.deanmcphee.jobtracker.dto.JobApplicationDto;
import com.deanmcphee.jobtracker.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<JobApplicationDto> getJobApplicationById(@PathVariable Long id) {
        JobApplication jobApplication = service.getById(id);
        JobApplicationDto responseDto = JobApplicationDto.fromEntity(jobApplication);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Create a new job application
     */
    @PostMapping
    public ResponseEntity<JobApplicationDto> createJobApplication(@RequestBody JobApplication job) {
        JobApplication jobApplication = service.save(job);
        JobApplicationDto responseDto = JobApplicationDto.fromEntity(jobApplication);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    /**
     * Delete an existing job application
     */
    @DeleteMapping("/{id}")
    public void deleteJobApplication(@PathVariable Long id) {
        service.deleteById(id);
    }

}