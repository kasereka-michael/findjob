package com.findjob_system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Entity
public class ResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private  long resumeId;
    @Getter
    @Setter
    private String fullName;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String email;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String education;
    @Getter
    @Setter
    private String firstPhoneNumber;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String secondPhoneNumber;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String experience;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private List<String> skills;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String languages;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String objective;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "userId" ,referencedColumnName = "userId")
    @Getter
    @Setter
    private JobSeeker jobSeeker;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String certifications;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String projects;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String hobbies;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String referencesDetails;
    @Lob
    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String additionalInfo;
    @Getter
    @Setter
    private String cvPath;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Getter
    @Setter
    private String maritialStatus;
    @Getter
    @Setter
    private String jobSeekerImage;


    public void setDateOfBirth(String dateOfBirthString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.dateOfBirth = dateFormat.parse(dateOfBirthString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDateOfBirth() {
        if (dateOfBirth != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            return dateFormat.format(dateOfBirth);
        }
        return null;
    }
}
