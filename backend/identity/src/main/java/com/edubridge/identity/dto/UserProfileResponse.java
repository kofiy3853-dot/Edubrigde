package com.edubridge.identity.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserProfileResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String avatarUrl;
    private String country;
    private String city;
    private String bio;
    private String website;
    private String linkedinUrl;
    private String timezone;
    private String language;
    private String currency;
    private Instant createdAt;
}
