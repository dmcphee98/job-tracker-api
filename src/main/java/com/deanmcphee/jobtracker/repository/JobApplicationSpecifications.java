package com.deanmcphee.jobtracker.repository;

import com.deanmcphee.jobtracker.model.ApplicationStatus;
import com.deanmcphee.jobtracker.model.JobApplication;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;


/**
 * Utility class containing {@link Specification} definitions for filtering {@link JobApplication} queries.
 * <p>
 * Each specification represents a single filter which can be combined dynamically
 * using {@link Specification#and(Specification)} or {@link Specification#or(Specification)}.
 * <p>
 * Any filter not provided (e.g., {@code null} or empty collections) will not affect the query.
 */
public class JobApplicationSpecifications {

    /**
     * Returns a {@link Specification} that filters {@link JobApplication} entities by
     * their {@link ApplicationStatus}.
     * <p>
     * Supports multiple statuses: if {@code statuses} contains multiple values,
     * the resulting SQL will use an {@code IN (...)} clause.
     *
     * @param statuses a collection of {@link ApplicationStatus} to filter by. May be {@code null} or empty
     * @return a {@link Specification} for filtering {@link JobApplication} entities by status
     */
    public static Specification<JobApplication> hasStatus(Collection<ApplicationStatus> statuses) {
        return (root, _, cb) -> {
            if (statuses == null || statuses.isEmpty()) {
                return cb.conjunction(); // No filtering, safe default
            }
            return root.get("status").in(statuses);
        };
    }

}