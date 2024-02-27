package com.findjob_system.repository;


import com.findjob_system.models.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker,Long> {

    boolean existsByUserEmail(String Email);
    JobSeeker findByUserEmail(String email);
    JobSeeker findByUserId(long userId);

}
