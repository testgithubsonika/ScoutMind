package com.scoutmind.service;

import com.scoutmind.entity.Candidate;
import com.scoutmind.entity.Job;

public interface AiExplanationService {
    String generateExplanation(Job job, Candidate candidate);
}
