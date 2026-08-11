package com.scoutmind.repository;

import com.scoutmind.entity.JobInvitation;
import com.scoutmind.enums.JobInvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobInvitationRepository extends JpaRepository<JobInvitation, Long> {

    /**
     * Find invitation by its secure token.
     */
    Optional<JobInvitation> findByInvitationToken(String invitationToken);

    /**
     * Get all invitations for a candidate.
     */
    List<JobInvitation> findByCandidateIdOrderByInvitedAtDesc(Long candidateId);

    /**
     * Check if an active (SENT, non-expired) invitation exists for job+candidate.
     */
    boolean existsByJobIdAndCandidateIdAndStatus(Long jobId, Long candidateId, JobInvitationStatus status);

    /**
     * Find active invitation for job+candidate combination.
     */
    Optional<JobInvitation> findByJobIdAndCandidateIdAndStatus(Long jobId, Long candidateId,
            JobInvitationStatus status);

    /**
     * Find all invitations for a job and list of candidates.
     */
    List<JobInvitation> findByJobIdAndCandidateIdIn(Long jobId, List<Long> candidateIds);
}
