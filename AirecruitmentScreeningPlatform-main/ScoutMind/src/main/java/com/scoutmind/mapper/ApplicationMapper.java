package com.scoutmind.mapper;

import com.scoutmind.dto.JobApplicationResponseDTO;
import com.scoutmind.entity.Application;
import org.springframework.stereotype.Component;

/**
 * Mapper for Application entity to JobApplicationResponseDTO.
 */
@Component
public class ApplicationMapper {

    public JobApplicationResponseDTO toDTO(Application application) {
        return new JobApplicationResponseDTO(
                application.getId(),
                application.getJob().getId(),
                application.getJob().getTitle(),
                application.getJob().getCompanyName(),
                application.getCandidate().getId(),
                application.getCandidate().getUser().getName(),
                application.getCandidate().getUser().getEmail(),
                application.getCandidate().getHeadline(),
                application.getCandidate().getLocation(),
                application.getCandidate().getSkills(),
                application.getCandidate().getExperienceYears(),
                application.getResume().getId(),
                application.getStatus(),
                application.getAiAnalysis(),
                application.getMatchScoreSnapshot() != null
                        ? (int) Math.round(application.getMatchScoreSnapshot() * 100)
                        : 0,
                application.getAppliedAt(),
                null, // updatedAt not available in entity yet
                application.getJob().getPostedBy().getId());
    }
}
