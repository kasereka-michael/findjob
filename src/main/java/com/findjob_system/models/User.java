package com.findjob_system.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.annotation.Transient;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
@Data
//@Table


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String userEmail;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be between 8 and 12 characters") // i set it to max 60 cause the password has to be encoded and when password is encoded it turn into 60 characters
    private String password;
    private String userName;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable (name = "user_roles", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Collection<Role> roles = new HashSet<>();
    private String resetToken;
    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, orphanRemoval = true)
    private Set<UserOPT> optcode = new HashSet<>();
    private boolean showEmail;
    private boolean viewProfile;
    private String profileImageUrl;
    private String linkedInProfile;
    private String twitterProfile;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    @LastModifiedBy
    private String modifiedBy;
    private boolean isCredentialsNonExpired;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isEnabled;
    private String mobilePhoneNumber;

    public User(String password){
        this.password = password;
    }
    public User(){
    }
}