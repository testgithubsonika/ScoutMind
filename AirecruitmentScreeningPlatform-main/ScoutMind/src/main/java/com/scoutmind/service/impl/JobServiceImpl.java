package com.scoutmind.service.impl;

import com.scoutmind.entity.Job;
import com.scoutmind.entity.User;
import com.scoutmind.enums.EmploymentType;
import com.scoutmind.enums.JobType;
import com.scoutmind.exception.ResourceNotFoundException;
import com.scoutmind.repository.JobRepository;
import com.scoutmind.service.JobService;
import com.scoutmind.specification.JobSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final VectorStore vectorStore;

    @Override
    @Transactional
    public Job createJob(Job job) {
        if (job.getJobReferenceId() == null || job.getJobReferenceId().trim().isEmpty()) {
            throw new IllegalArgumentException("Job Reference ID is required");
        }
        Job savedJob = jobRepository.save(job);
        saveJobToVectorStore(savedJob);
        return savedJob;
    }

    @Override
    public Job getJob(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAllPublicJobs();
    }

    @Override
    public List<Job> getJobsByRecruiter(User recruiter) {
        return jobRepository.findByPostedBy(recruiter);
    }

    @Override
    @Transactional
    public Job updateJob(Long id, Job updatedDetails) {
        Job savedJob = jobRepository.save(updatedDetails);
        deleteJobFromVectorStore(id);
        saveJobToVectorStore(savedJob);
        return savedJob;
    }

    @Override
    @Transactional
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new ResourceNotFoundException("Job not found with id: " + id);
        }
        jobRepository.deleteById(id);
        deleteJobFromVectorStore(id);
    }

    @Override
    public List<Job> searchJobs(String query) {
        List<Document> similarDocuments = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(5)
                        .filterExpression(new FilterExpressionBuilder().eq("docType", "JOB")
                                .build())
                        .build());

        List<Long> jobIds = similarDocuments.stream()
                .map(doc -> Long.valueOf(doc.getMetadata().get("jobId").toString()))
                .collect(Collectors.toList());

        return jobRepository.findAllById(jobIds);
    }

    @Override
    public List<Job> filterJobs(JobType jobType, EmploymentType employmentType, String location,
            BigDecimal minSalary, BigDecimal maxSalary, String skill) {
        Specification<Job> spec = JobSpecification.filterJobs(jobType, employmentType, location, minSalary,
                maxSalary, skill);
        return jobRepository.findAll(spec);
    }

    private void saveJobToVectorStore(Job job) {
        String jobContent = "Job Title: " + job.getTitle() +
                "\nDescription: " + job.getDescription() +
                "\nSkills: " + String.join(", ", job.getSkillsRequired()) +
                "\nLocation: " + job.getLocation();

        String vectorDocId = java.util.UUID.nameUUIDFromBytes(("job_" + job.getId()).getBytes()).toString();

        Document document = new Document(vectorDocId, jobContent, Map.of(
                "jobId", job.getId(),
                "jobType", job.getJobType().name(),
                "docType", "JOB"));

        vectorStore.add(List.of(document));
        log.info("Saved job embedding with docId: {}", vectorDocId);
    }

    private void deleteJobFromVectorStore(Long jobId) {
        String vectorDocId = java.util.UUID.nameUUIDFromBytes(("job_" + jobId).getBytes()).toString();
        try {
            vectorStore.delete(List.of(vectorDocId));
            log.info("Deleted job embedding with docId: {}", vectorDocId);
        } catch (Exception e) {
            log.warn("Failed to delete job embedding for jobId: {} with docId: {}", jobId, vectorDocId, e);
        }
    }
}
