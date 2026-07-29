package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.SessionRecord;
import com.edubridge.studentservices.repositories.SessionRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionRecordService {

    private final SessionRecordRepository sessionRecordRepository;

    @Transactional
    public SessionRecord createSession(SessionRecord session) {
        log.info("Creating session for consultation: {} student: {}", session.getConsultationId(), session.getStudentId());
        return sessionRecordRepository.save(session);
    }

    @Transactional(readOnly = true)
    public SessionRecord getSessionById(String id) {
        return sessionRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<SessionRecord> getSessionsByConsultation(String consultationId) {
        return sessionRecordRepository.findByConsultationIdAndIsActiveTrue(consultationId);
    }

    @Transactional(readOnly = true)
    public List<SessionRecord> getSessionsByStudent(String studentId) {
        return sessionRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<SessionRecord> getSessionsByAdvisor(String advisorId) {
        return sessionRecordRepository.findByAdvisorIdAndIsActiveTrue(advisorId);
    }

    @Transactional(readOnly = true)
    public List<SessionRecord> getSessionsByStatus(String sessionStatus) {
        return sessionRecordRepository.findBySessionStatusAndIsActiveTrue(sessionStatus);
    }

    @Transactional(readOnly = true)
    public List<SessionRecord> getCompletedByStudent(String studentId) {
        return sessionRecordRepository.findCompletedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<SessionRecord> getScheduledByAdvisor(String advisorId) {
        return sessionRecordRepository.findScheduledByAdvisor(advisorId);
    }

    @Transactional(readOnly = true)
    public List<SessionRecord> getRequiringFollowUp() {
        return sessionRecordRepository.findRequiringFollowUp();
    }

    @Transactional
    public SessionRecord updateSession(String id, SessionRecord updatedSession) {
        SessionRecord existing = getSessionById(id);
        existing.setSessionType(updatedSession.getSessionType());
        existing.setSessionStatus(updatedSession.getSessionStatus());
        existing.setScheduledStart(updatedSession.getScheduledStart());
        existing.setScheduledEnd(updatedSession.getScheduledEnd());
        existing.setActualStart(updatedSession.getActualStart());
        existing.setActualEnd(updatedSession.getActualEnd());
        existing.setSessionNotes(updatedSession.getSessionNotes());
        existing.setActionItems(updatedSession.getActionItems());
        existing.setFollowUpRequired(updatedSession.getFollowUpRequired());
        existing.setActive(updatedSession.getIsActive());
        return sessionRecordRepository.save(existing);
    }

    @Transactional
    public SessionRecord completeSession(String id, String notes, String actionItems) {
        SessionRecord existing = getSessionById(id);
        existing.setSessionStatus("COMPLETED");
        existing.setActualEnd(LocalDateTime.now());
        existing.setSessionNotes(notes);
        existing.setActionItems(actionItems);
        return sessionRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateSession(String id) {
        SessionRecord existing = getSessionById(id);
        existing.setActive(false);
        sessionRecordRepository.save(existing);
    }
}
