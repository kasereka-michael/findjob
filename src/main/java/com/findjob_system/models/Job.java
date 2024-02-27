package com.findjob_system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "userId" ,referencedColumnName = "userId")
    private Employers employer;

    private String jobTitle;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String jobDescription;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String jobRequirement;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_At;

    private String filePath;

}
