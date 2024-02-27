package com.findjob_system.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@PrimaryKeyJoinColumn(referencedColumnName = "userId")
public class JobSeeker extends User{
    private String firstName;
    private String lastName;
    private Boolean userStatus;
    private List<String> qualities = new ArrayList<>();
    private String homePhoneNumber;
    private boolean showPhoneNumber;
    private String address;
    private String city;
    private String country;
    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.DETACH)
    private Set<ResumeEntity> cvsBuilt = new HashSet<ResumeEntity>();
}
