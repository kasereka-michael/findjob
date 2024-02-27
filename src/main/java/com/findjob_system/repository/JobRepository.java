package com.findjob_system.repository;


import com.findjob_system.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("select p from Job p where p.jobTitle like %?1% or p.jobDescription like %?1%")
    List<Job> searchJob(String keyword);

}
