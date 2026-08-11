package com.scoutmind.service;

import com.scoutmind.entity.Job;
import com.scoutmind.entity.User;
import com.scoutmind.enums.EmploymentType;
import com.scoutmind.enums.JobType;

import java.math.BigDecimal;
import java.util.List;

public interface JobService {
        Job createJob(Job job);

        Job getJob(Long id);

        List<Job> getAllJobs();

        List<Job> getJobsByRecruiter(User recruiter);

        Job updateJob(Long id, Job updatedDetails);

        void deleteJob(Long id);

        List<Job> searchJobs(String query);

        List<Job> filterJobs(JobType jobType, EmploymentType employmentType, String location,
                        BigDecimal minSalary, BigDecimal maxSalary, String skill);
}
