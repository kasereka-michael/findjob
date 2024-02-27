package com.findjob_system.Services.EmailService;

import com.findjob_system.models.User;

import java.util.List;

public interface EmailService {
    Boolean confirmAccountEmail(String name, String to,String token);
    void notificationOfPostedJob(String name, List<User> toList, String token);

    Boolean sendOPtCode(String name,String email,int opt);
}
