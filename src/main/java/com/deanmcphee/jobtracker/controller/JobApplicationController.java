package com.deanmcphee.jobtracker.controller;

import com.deanmcphee.jobtracker.dto.JobApplicationCreateDto;
import com.deanmcphee.jobtracker.dto.JobApplicationDto;
import com.deanmcphee.jobtracker.dto.JobApplicationPatchDto;
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
        JobApplicationDto jobApplication = service.getById(id);
        return ResponseEntity.ok(jobApplication);
    }

    /**
     * Create a new job application
     */
    @PostMapping
    public ResponseEntity<JobApplicationDto> createJobApplication(@RequestBody JobApplicationCreateDto request) {
        JobApplicationDto created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Fully updates an existing job application
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationDto> patchJobApplication(@PathVariable Long id, @RequestBody JobApplicationCreateDto request) {
        JobApplicationDto patched = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(patched);
    }

    /**
     * Partially updates an existing job application
     */
    @PatchMapping("/{id}")
    public ResponseEntity<JobApplicationDto> patchJobApplication(@PathVariable Long id, @RequestBody JobApplicationPatchDto request) {
        JobApplicationDto patched = service.patch(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(patched);
    }

    /**
     * Delete an existing job application
     */
    @DeleteMapping("/{id}")
    public void deleteJobApplication(@PathVariable Long id) {
        service.deleteById(id);
    }

}