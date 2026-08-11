package com.scoutmind.dto;

import com.scoutmind.enums.ApplicationStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Job application details including AI analysis")
public record JobApplicationResponseDTO(
                @Schema(description = "Application ID") Long id,
                @Schema(description = "Job ID") Long jobId,
                @Schema(description = "Job title") String jobTitle,
                @Schema(description = "Company name") String companyName,
                @Schema(description = "Candidate user ID") Long candidateId,
                @Schema(description = "Candidate name") String candidateName,
                @Schema(description = "Candidate email") String candidateEmail,
                @Schema(description = "Candidate headline") String candidateHeadline,
                @Schema(description = "Candidate location") String candidateLocation,
                @Schema(description = "Candidate skills") java.util.List<String> candidateSkills,
                @Schema(description = "Candidate experience in years") Integer candidateExperience,
                @Schema(description = "Resume ID used") Long resumeId,
                @Schema(description = "Current application status") ApplicationStatus status,
                @Schema(description = "AI-generated analysis of candidate fit") String aiAnalysis,
                @Schema(description = "Match score as percentage (0-100)") Integer matchScoreSnapshot,
                @Schema(description = "Application submission timestamp") LocalDateTime appliedAt,
                @Schema(description = "Last status update timestamp") LocalDateTime updatedAt,
                @Schema(description = "Recruiter user ID (job owner)") Long recruiterId) {
}
