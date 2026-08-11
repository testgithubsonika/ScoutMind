package com.scoutmind.service;

import com.scoutmind.dto.InvitationAcceptResponseDTO;
import com.scoutmind.dto.InvitationResponseDTO;
import com.scoutmind.dto.InviteCandidateRequestDTO;

import java.util.List;

public interface JobInvitationService {
        InvitationResponseDTO inviteCandidate(Long jobId, InviteCandidateRequestDTO request, String recruiterEmail);

        List<InvitationResponseDTO> getCandidateInvitations(String candidateEmail);

        InvitationAcceptResponseDTO acceptInvitation(String token, String candidateEmail);

        InvitationResponseDTO declineInvitation(String token, String candidateEmail);
}
