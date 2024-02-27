package com.findjob_system.repository;

import com.findjob_system.models.User;
import com.findjob_system.models.UserOPT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserOPTRepository extends JpaRepository<UserOPT, Long> {
    Optional<UserOPT> findByUserOpt(int opt);
}
