package com.findjob_system.repository;

import com.findjob_system.models.JobSeeker;
import com.findjob_system.models.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeBuilderRepository extends JpaRepository<ResumeEntity, Long> {

    ResumeEntity findByJobSeeker(JobSeeker jobSeeker);

}
