package com.scoutmind.event;

import com.scoutmind.entity.InterviewSchedule;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Domain event published when a new interview is scheduled.
 * Triggers email notification with calendar invite.
 */
@Getter
public class InterviewScheduledEvent extends ApplicationEvent {

    private final InterviewSchedule interview;

    public InterviewScheduledEvent(Object source, InterviewSchedule interview) {
        super(source);
        this.interview = interview;
    }
}
