package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.SuccessPlanRecord;
import com.edubridge.learningservices.repositories.SuccessPlanRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuccessPlanRecordService {

    private final SuccessPlanRecordRepository successPlanRecordRepository;

    @Transactional
    public SuccessPlanRecord createSuccessPlanRecord(SuccessPlanRecord record) {
        log.info("Creating success plan record for student: {} type: {}", record.getStudentId(), record.getPlanType());
        return successPlanRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public SuccessPlanRecord getSuccessPlanRecordById(String id) {
        return successPlanRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Success plan record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<SuccessPlanRecord> getSuccessPlanRecordsByStudent(String studentId) {
        return successPlanRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<SuccessPlanRecord> getSuccessPlanRecordsByGrowthProfile(String growthProfileId) {
        return successPlanRecordRepository.findByGrowthProfileIdAndIsActiveTrue(growthProfileId);
    }

    @Transactional(readOnly = true)
    public List<SuccessPlanRecord> getSuccessPlanRecordsByType(String planType) {
        return successPlanRecordRepository.findByPlanTypeAndIsActiveTrue(planType);
    }

    @Transactional(readOnly = true)
    public List<SuccessPlanRecord> getSuccessPlanRecordsByStatus(String planStatus) {
        return successPlanRecordRepository.findByPlanStatusAndIsActiveTrue(planStatus);
    }

    @Transactional(readOnly = true)
    public List<SuccessPlanRecord> getSuccessPlanRecordsByPriority(String priorityLevel) {
        return successPlanRecordRepository.findByPriorityLevelAndIsActiveTrue(priorityLevel);
    }

    @Transactional(readOnly = true)
    public List<SuccessPlanRecord> getActivePlansByStudent(String studentId) {
        return successPlanRecordRepository.findActivePlansByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<SuccessPlanRecord> getSuccessPlanByStudentAndType(String studentId, String planType) {
        return successPlanRecordRepository.findByStudentAndPlanType(studentId, planType);
    }

    @Transactional(readOnly = true)
    public List<SuccessPlanRecord> getOverduePlans() {
        return successPlanRecordRepository.findOverduePlans();
    }

    @Transactional
    public SuccessPlanRecord updateSuccessPlanRecord(String id, SuccessPlanRecord updated) {
        SuccessPlanRecord existing = getSuccessPlanRecordById(id);
        existing.setPlanType(updated.getPlanType());
        existing.setPlanName(updated.getPlanName());
        existing.setPlanStatus(updated.getPlanStatus());
        existing.setPlanDescription(updated.getPlanDescription());
        existing.setTargetCompletionDate(updated.getTargetCompletionDate());
        existing.setPriorityLevel(updated.getPriorityLevel());
        existing.setTotalGoals(updated.getTotalGoals());
        existing.setCompletedGoals(updated.getCompletedGoals());
        existing.setGoalCompletionPercentage(updated.getGoalCompletionPercentage());
        existing.setSuccessMetrics(updated.getSuccessMetrics());
        existing.setActionItems(updated.getActionItems());
        existing.setResponsibleAdvisorId(updated.getResponsibleAdvisorId());
        existing.setLastReviewDate(updated.getLastReviewDate());
        existing.setNextReviewDate(updated.getNextReviewDate());
        existing.setNotes(updated.getNotes());
        return successPlanRecordRepository.save(existing);
    }

    @Transactional
    public SuccessPlanRecord completeSuccessPlanRecord(String id) {
        SuccessPlanRecord existing = getSuccessPlanRecordById(id);
        existing.setPlanStatus("COMPLETED");
        existing.setActualCompletionDate(LocalDateTime.now());
        existing.setCompletedGoals(existing.getTotalGoals());
        existing.setGoalCompletionPercentage(100.0);
        return successPlanRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateSuccessPlanRecord(String id) {
        SuccessPlanRecord existing = getSuccessPlanRecordById(id);
        existing.setActive(false);
        successPlanRecordRepository.save(existing);
    }
}
