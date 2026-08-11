package com.scoutmind.service;

import com.scoutmind.entity.InterviewSchedule;
import com.scoutmind.entity.JobInvitation;

public interface NotificationService {
    void sendJobInvitationEmail(JobInvitation invitation);

    void sendInterviewScheduledNotification(InterviewSchedule interview);

    void sendInterviewRescheduledNotification(InterviewSchedule interview);

    void sendInterviewCancelledNotification(InterviewSchedule interview, String reason);
}
