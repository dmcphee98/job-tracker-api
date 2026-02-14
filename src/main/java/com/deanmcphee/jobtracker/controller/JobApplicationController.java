package com.deanmcphee.jobtracker.controller;

import com.deanmcphee.jobtracker.dto.JobApplicationCreateDto;
import com.deanmcphee.jobtracker.dto.JobApplicationDto;
import com.deanmcphee.jobtracker.dto.JobApplicationFilterDto;
import com.deanmcphee.jobtracker.dto.JobApplicationPatchDto;
import com.deanmcphee.jobtracker.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * Get job applications
     */
    @GetMapping
    public ResponseEntity<List<JobApplicationDto>> getJobApplications(JobApplicationFilterDto filter) {
        List<JobApplicationDto> found = service.getAll(filter);
        return ResponseEntity.ok(found);
    }

    /**
     * Get job application by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationDto> getJobApplicationById(@PathVariable Long id) {
        JobApplicationDto found = service.getById(id);
        return ResponseEntity.ok(found);
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
    public ResponseEntity<JobApplicationDto> putJobApplication(@PathVariable Long id, @RequestBody JobApplicationCreateDto request) {
        JobApplicationDto updated = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
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