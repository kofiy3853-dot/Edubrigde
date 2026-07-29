package com.edubridge.identity.services;

import com.edubridge.identity.dto.*;
import com.edubridge.identity.entities.User;
import com.edubridge.identity.entities.UserPreferences;
import com.edubridge.identity.entities.UserProfile;
import com.edubridge.identity.repositories.UserPreferencesRepository;
import com.edubridge.identity.repositories.UserProfileRepository;
import com.edubridge.identity.repositories.UserRepository;
import com.edubridge.shared.exceptions.BadRequestException;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserManagementService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserPreferencesRepository userPreferencesRepository;

    @Transactional
    public void initializeUserProfile(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!userProfileRepository.existsByUserId(userId)) {
            UserProfile profile = UserProfile.builder()
                .user(user)
                .timezone("UTC")
                .language("en")
                .currency("USD")
                .build();
            userProfileRepository.save(profile);
            log.info("User profile initialized: userId={}", userId);
        }

        if (!userPreferencesRepository.existsByUserId(userId)) {
            UserPreferences preferences = UserPreferences.builder()
                .user(user)
                .build();
            userPreferencesRepository.save(preferences);
            log.info("User preferences initialized: userId={}", userId);
        }
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getProfile(UUID userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        User user = profile.getUser();

        UserProfileResponse response = new UserProfileResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setAvatarUrl(user.getAvatarUrl());
        response.setCountry(profile.getCountry());
        response.setCity(profile.getCity());
        response.setBio(profile.getBio());
        response.setWebsite(profile.getWebsite());
        response.setLinkedinUrl(profile.getLinkedinUrl());
        response.setTimezone(profile.getTimezone());
        response.setLanguage(profile.getLanguage());
        response.setCurrency(profile.getCurrency());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    @Transactional
    public UserProfileResponse updateProfile(UUID userId, UpdateProfileRequest request) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        if (request.getCountry() != null) profile.setCountry(request.getCountry());
        if (request.getCity() != null) profile.setCity(request.getCity());
        if (request.getAddress() != null) profile.setAddress(request.getAddress());
        if (request.getPostalCode() != null) profile.setPostalCode(request.getPostalCode());
        if (request.getBio() != null) profile.setBio(request.getBio());
        if (request.getWebsite() != null) profile.setWebsite(request.getWebsite());
        if (request.getLinkedinUrl() != null) profile.setLinkedinUrl(request.getLinkedinUrl());
        if (request.getTimezone() != null) profile.setTimezone(request.getTimezone());
        if (request.getLanguage() != null) profile.setLanguage(request.getLanguage());
        if (request.getCurrency() != null) profile.setCurrency(request.getCurrency());

        userProfileRepository.save(profile);
        log.info("Profile updated: userId={}", userId);

        return getProfile(userId);
    }

    @Transactional(readOnly = true)
    public UserPreferencesResponse getPreferences(UUID userId) {
        UserPreferences preferences = userPreferencesRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Preferences not found"));

        UserPreferencesResponse response = new UserPreferencesResponse();
        response.setEmailNotifications(preferences.getEmailNotifications());
        response.setSmsNotifications(preferences.getSmsNotifications());
        response.setPushNotifications(preferences.getPushNotifications());
        response.setMarketingEmails(preferences.getMarketingEmails());
        response.setSessionReminders(preferences.getSessionReminders());
        response.setPaymentNotifications(preferences.getPaymentNotifications());
        response.setSecurityAlerts(preferences.getSecurityAlerts());
        response.setAcademicUpdates(preferences.getAcademicUpdates());
        response.setDarkMode(preferences.getDarkMode());
        response.setCompactMode(preferences.getCompactMode());
        response.setSidebarCollapsed(preferences.getSidebarCollapsed());
        response.setTwoFactorEnabled(preferences.getTwoFactorEnabled());
        response.setSessionTimeoutMinutes(preferences.getSessionTimeoutMinutes());
        response.setLoginNotifications(preferences.getLoginNotifications());
        return response;
    }

    @Transactional
    public UserPreferencesResponse updatePreferences(UUID userId, UpdatePreferencesRequest request) {
        UserPreferences preferences = userPreferencesRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Preferences not found"));

        if (request.getEmailNotifications() != null) preferences.setEmailNotifications(request.getEmailNotifications());
        if (request.getSmsNotifications() != null) preferences.setSmsNotifications(request.getSmsNotifications());
        if (request.getPushNotifications() != null) preferences.setPushNotifications(request.getPushNotifications());
        if (request.getMarketingEmails() != null) preferences.setMarketingEmails(request.getMarketingEmails());
        if (request.getSessionReminders() != null) preferences.setSessionReminders(request.getSessionReminders());
        if (request.getPaymentNotifications() != null) preferences.setPaymentNotifications(request.getPaymentNotifications());
        if (request.getSecurityAlerts() != null) preferences.setSecurityAlerts(request.getSecurityAlerts());
        if (request.getAcademicUpdates() != null) preferences.setAcademicUpdates(request.getAcademicUpdates());
        if (request.getDarkMode() != null) preferences.setDarkMode(request.getDarkMode());
        if (request.getCompactMode() != null) preferences.setCompactMode(request.getCompactMode());
        if (request.getSidebarCollapsed() != null) preferences.setSidebarCollapsed(request.getSidebarCollapsed());
        if (request.getTwoFactorEnabled() != null) preferences.setTwoFactorEnabled(request.getTwoFactorEnabled());
        if (request.getSessionTimeoutMinutes() != null) preferences.setSessionTimeoutMinutes(request.getSessionTimeoutMinutes());
        if (request.getLoginNotifications() != null) preferences.setLoginNotifications(request.getLoginNotifications());

        userPreferencesRepository.save(preferences);
        log.info("Preferences updated: userId={}", userId);

        return getPreferences(userId);
    }

    @Transactional
    public void updateAccountStatus(UUID userId, String status) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setStatus(status);
        userRepository.save(user);
        log.info("Account status updated: userId={}, status={}", userId, status);
    }
}
