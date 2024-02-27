package com.findjob_system.models.Dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private Boolean userStatus;
    private List<String> qualities = new ArrayList<>();
    private String homePhoneNumber;
    private boolean showPhoneNumber;
    private String password;
    private String address;
    private String city;
    private String country;
    private boolean viewProfile;
    private String profileImageUrl;
    private String linkedInProfile;
    private String twitterProfile;
    private boolean isCredentialsNonExpired;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isEnabled;
    private String mobilePhoneNumber;

}
