package com.scoutmind.service;

import com.scoutmind.dto.CancelInterviewRequestDTO;
import com.scoutmind.dto.InterviewResponseDTO;
import com.scoutmind.dto.RescheduleInterviewRequestDTO;
import com.scoutmind.dto.ScheduleInterviewRequestDTO;

import java.util.List;

public interface InterviewScheduleService {
        InterviewResponseDTO scheduleInterview(Long applicationId, ScheduleInterviewRequestDTO request,
                        String recruiterEmail);

        InterviewResponseDTO rescheduleInterview(Long interviewId, RescheduleInterviewRequestDTO request,
                        String recruiterEmail);

        InterviewResponseDTO cancelInterview(Long interviewId, CancelInterviewRequestDTO request,
                        String recruiterEmail);

        List<InterviewResponseDTO> getCandidateInterviews(String candidateEmail);

        List<InterviewResponseDTO> getRecruiterInterviewsForJob(Long jobId, String recruiterEmail);

        List<InterviewResponseDTO> getInterviewsForRecruiter(String recruiterEmail);
}
