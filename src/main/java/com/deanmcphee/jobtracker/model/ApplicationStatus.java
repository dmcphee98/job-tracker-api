package com.deanmcphee.jobtracker.model;

/**
 * Represents the possible statuses of a job application.
 * Each value indicates a stage in the application process:
 * <ul>
 *     <li>{@link #APPLIED} – The application has been submitted but not yet processed.</li>
 *     <li>{@link #INTERVIEWING} – The applicant is currently in the interview process.</li>
 *     <li>{@link #REJECTED} – The application has been rejected.</li>
 *     <li>{@link #OFFER} – An offer has been made to the applicant.</li>
 * </ul>
 */
public enum ApplicationStatus {
    APPLIED,
    INTERVIEWING,
    REJECTED,
    OFFER,
}