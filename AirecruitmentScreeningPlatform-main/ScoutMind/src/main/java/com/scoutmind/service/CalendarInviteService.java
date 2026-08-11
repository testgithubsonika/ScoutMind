package com.scoutmind.service;

import com.scoutmind.entity.InterviewSchedule;

public interface CalendarInviteService {
    String generateScheduleInvite(InterviewSchedule interview);

    String generateRescheduleInvite(InterviewSchedule interview);

    String generateCancelInvite(InterviewSchedule interview);
}
