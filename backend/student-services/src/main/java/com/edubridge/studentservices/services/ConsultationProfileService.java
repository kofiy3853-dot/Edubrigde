package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.ConsultationProfile;
import com.edubridge.studentservices.repositories.ConsultationProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultationProfileService {

    private final ConsultationProfileRepository consultationProfileRepository;

    @Transactional
    public ConsultationProfile createConsultation(ConsultationProfile consultation) {
        log.info("Creating consultation for student: {} advisor: {}", consultation.getStudentId(), consultation.getAdvisorId());
        return consultationProfileRepository.save(consultation);
    }

    @Transactional(readOnly = true)
    public ConsultationProfile getConsultationById(String id) {
        return consultationProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<ConsultationProfile> getConsultationsByStudent(String studentId) {
        return consultationProfileRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<ConsultationProfile> getConsultationsByAdvisor(String advisorId) {
        return consultationProfileRepository.findByAdvisorIdAndIsActiveTrue(advisorId);
    }

    @Transactional(readOnly = true)
    public List<ConsultationProfile> getConsultationsByType(String consultationType) {
        return consultationProfileRepository.findByConsultationTypeAndIsActiveTrue(consultationType);
    }

    @Transactional(readOnly = true)
    public List<ConsultationProfile> getConsultationsByStatus(String consultationStatus) {
        return consultationProfileRepository.findByConsultationStatusAndIsActiveTrue(consultationStatus);
    }

    @Transactional(readOnly = true)
    public List<ConsultationProfile> getScheduledByStudent(String studentId) {
        return consultationProfileRepository.findScheduledByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<ConsultationProfile> getScheduledByAdvisor(String advisorId) {
        return consultationProfileRepository.findScheduledByAdvisor(advisorId);
    }

    @Transactional(readOnly = true)
    public List<ConsultationProfile> getPendingConsultations() {
        return consultationProfileRepository.findPendingConsultations();
    }

    @Transactional
    public ConsultationProfile updateConsultation(String id, ConsultationProfile updatedConsultation) {
        ConsultationProfile existing = getConsultationById(id);
        existing.setConsultationType(updatedConsultation.getConsultationType());
        existing.setConsultationStatus(updatedConsultation.getConsultationStatus());
        existing.setScheduledDate(updatedConsultation.getScheduledDate());
        existing.setDuration(updatedConsultation.getDuration());
        existing.setTopic(updatedConsultation.getTopic());
        existing.setDescription(updatedConsultation.getDescription());
        existing.setPriority(updatedConsultation.getPriority());
        existing.setVirtual(updatedConsultation.getIsVirtual());
        existing.setMeetingLink(updatedConsultation.getMeetingLink());
        existing.setNotes(updatedConsultation.getNotes());
        existing.setActive(updatedConsultation.getIsActive());
        return consultationProfileRepository.save(existing);
    }

    @Transactional
    public ConsultationProfile cancelConsultation(String id) {
        ConsultationProfile existing = getConsultationById(id);
        existing.setConsultationStatus("CANCELLED");
        return consultationProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateConsultation(String id) {
        ConsultationProfile existing = getConsultationById(id);
        existing.setActive(false);
        consultationProfileRepository.save(existing);
    }
}
