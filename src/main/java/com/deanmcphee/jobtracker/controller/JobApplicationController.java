package com.deanmcphee.jobtracker.controller;

import com.deanmcphee.jobtracker.dto.JobApplicationCreateDto;
import com.deanmcphee.jobtracker.dto.JobApplicationDto;
import com.deanmcphee.jobtracker.dto.JobApplicationFilterDto;
import com.deanmcphee.jobtracker.dto.JobApplicationPatchDto;
import com.deanmcphee.jobtracker.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
     * Extracts the unique user identifier from a JWT.
     * @param jwt the authenticated JWT injected by Spring Security
     * @return the unique user identifier (JWT "sub" claim)
     */
    private String getUserId(Jwt jwt) {
        return jwt.getClaimAsString("sub");
    }

    /**
     * Get job applications
     */
    @GetMapping
    public ResponseEntity<List<JobApplicationDto>> getJobApplications(
            JobApplicationFilterDto filter,
            @AuthenticationPrincipal Jwt jwt) {

        List<JobApplicationDto> found = service.getAll(filter, getUserId(jwt));
        return ResponseEntity.ok(found);
    }

    /**
     * Get job application by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationDto> getJobApplicationById(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt jwt) {

        JobApplicationDto found = service.getById(id, getUserId(jwt));
        return ResponseEntity.ok(found);
    }

    /**
     * Create a new job application
     */
    @PostMapping
    public ResponseEntity<JobApplicationDto> createJobApplication(
            @RequestBody JobApplicationCreateDto request,
            @AuthenticationPrincipal Jwt jwt) {

        JobApplicationDto created = service.create(request, getUserId(jwt));
        // RFC 9110 demands that a 201 Created response feature a Location header
        // containing the URI of the created resource.
        URI location = URI.create("/api/job-applications/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    /**
     * Fully updates an existing job application
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationDto> putJobApplication(
            @PathVariable Long id,
            @RequestBody JobApplicationCreateDto request,
            @AuthenticationPrincipal Jwt jwt) {

        JobApplicationDto updated = service.update(id, request, getUserId(jwt));
        return ResponseEntity.ok(updated);
    }

    /**
     * Partially updates an existing job application
     */
    @PatchMapping("/{id}")
    public ResponseEntity<JobApplicationDto> patchJobApplication(
            @PathVariable Long id,
            @RequestBody JobApplicationPatchDto request,
            @AuthenticationPrincipal Jwt jwt) {

        JobApplicationDto patched = service.patch(id, request, getUserId(jwt));
        return ResponseEntity.ok(patched);
    }

    /**
     * Delete an existing job application
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobApplication(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt jwt) {

        service.deleteById(id, getUserId(jwt));
        return ResponseEntity.noContent().build();
    }

}