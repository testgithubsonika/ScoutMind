package com.scoutmind.service;

import com.scoutmind.dto.RecommendedJobResponse;
import com.scoutmind.entity.User;

import java.util.List;

public interface CandidateJobRecommendationService {
    List<RecommendedJobResponse> getRecommendations(User user, int topK, Double minScore, String locationFilter);

    String getExplanation(Long userId, Long jobId);
}
