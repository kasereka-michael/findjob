package com.findjob_system.repository;

import com.findjob_system.models.Employers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employers, Long> {

}
