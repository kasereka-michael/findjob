package com.findjob_system.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;


@Entity
@Data
@PrimaryKeyJoinColumn(referencedColumnName = "userId")
public class Employers extends User{
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String logoUrlBase;
    @Column(columnDefinition = "TEXT")
    private String CompanyDescription;
    @OneToMany(mappedBy = "employer", cascade = CascadeType.DETACH)
    private Set<Job> jobPosts;
}
