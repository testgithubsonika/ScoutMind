package com.scoutmind.service;

import com.scoutmind.dto.MatchedCandidateDTO;

import java.util.List;

public interface JobMatchingService {
    List<MatchedCandidateDTO> getMatchedCandidates(Long jobId, int topK, String recruiterEmail);
}
