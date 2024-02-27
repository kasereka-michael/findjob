package com.findjob_system.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Random;

@Data
@Entity
public class UserOPT{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int userOpt;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    public UserOPT(User user){
        Random random = new Random();
        int min = (int) Math.pow(10, 5 - 1);
        int max = (int) Math.pow(10, 5) - 1;
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.userOpt = random.nextInt(max - min + 1) + min;
    }

    public UserOPT(){
    }


}
