package com.scoutmind.mapper;

import com.scoutmind.dto.CandidateProfileDTO;
import com.scoutmind.dto.RecruiterProfileDTO;
import com.scoutmind.dto.UserCreateDTO;
import com.scoutmind.dto.UserResponseDTO;
import com.scoutmind.dto.UserUpdateDTO;
import com.scoutmind.entity.Candidate;
import com.scoutmind.entity.Recruiter;
import com.scoutmind.entity.User;
import com.scoutmind.enums.UserRole;
import com.scoutmind.repository.CandidateRepository;
import com.scoutmind.repository.RecruiterRepository;
import com.scoutmind.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CandidateRepository candidateRepository;
    private final RecruiterRepository recruiterRepository;
    private final ResumeRepository resumeRepository;

    public User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setRole(dto.role());
        user.setBio(dto.bio());
        user.setLinkedInUrl(dto.linkedInUrl());
        user.setPortfolioUrl(dto.portfolioUrl());
        return user;
    }

    public void updateEntity(User user, UserUpdateDTO dto) {
        if (dto.name() != null)
            user.setName(dto.name());
        if (dto.bio() != null)
            user.setBio(dto.bio());
        if (dto.linkedInUrl() != null)
            user.setLinkedInUrl(dto.linkedInUrl());
        if (dto.portfolioUrl() != null)
            user.setPortfolioUrl(dto.portfolioUrl());
    }

    public void updateRecruiter(Recruiter recruiter, UserUpdateDTO dto) {
        if (dto.companyName() != null)
            recruiter.setCompanyName(dto.companyName());
        if (dto.designation() != null)
            recruiter.setDesignation(dto.designation());
        if (dto.companyWebsite() != null)
            recruiter.setCompanyWebsite(dto.companyWebsite());
    }

    public UserResponseDTO toDTO(User user) {
        CandidateProfileDTO candidateProfile = null;
        RecruiterProfileDTO recruiterProfile = null;

        if (user.getRole() == UserRole.CANDIDATE) {
            Candidate candidate = candidateRepository.findByUserId(user.getId()).orElse(null);
            if (candidate != null) {
                Long resumeId = resumeRepository.findByUserId(user.getId())
                        .map(com.scoutmind.entity.Resume::getId)
                        .orElse(null);

                candidateProfile = new CandidateProfileDTO(
                        candidate.getSkills(),
                        candidate.getExperienceYears(),
                        candidate.getHeadline(),
                        candidate.getLocation(),
                        resumeId);
            }
        } else if (user.getRole() == UserRole.RECRUITER) {
            Recruiter recruiter = recruiterRepository.findByUserId(user.getId()).orElse(null);
            if (recruiter != null) {
                recruiterProfile = new RecruiterProfileDTO(
                        recruiter.getCompanyName(),
                        recruiter.getDesignation(),
                        recruiter.getCompanyWebsite());
            }
        }

        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole().name(),
                user.getBio(),
                user.getLinkedInUrl(),
                user.getPortfolioUrl(),
                candidateProfile,
                recruiterProfile,
                user.getCreatedAt());
    }
}
