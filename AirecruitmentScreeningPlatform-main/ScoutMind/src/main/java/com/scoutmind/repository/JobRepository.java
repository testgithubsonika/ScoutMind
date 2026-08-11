package com.scoutmind.repository;

import com.scoutmind.entity.Job;
import com.scoutmind.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface JobRepository extends JpaSpecificationExecutor<Job>, JpaRepository<Job, Long> {
    List<Job> findByPostedBy(User user);

    long countByPostedByAndActiveTrue(User user);

    @org.springframework.data.jpa.repository.Query("SELECT j FROM Job j WHERE j.active = true AND (j.applicationDeadline IS NULL OR j.applicationDeadline >= CURRENT_DATE)")
    List<Job> findAllPublicJobs();

    @org.springframework.data.jpa.repository.Query("SELECT j FROM Job j WHERE j.active = true AND j.applicationDeadline < CURRENT_DATE")
    List<Job> findExpiredActiveJobs();
}
