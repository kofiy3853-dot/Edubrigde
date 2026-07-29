package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.CourseProfile;
import com.edubridge.learningservices.repositories.CourseProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseProfileService {

    private final CourseProfileRepository courseProfileRepository;

    @Transactional
    public CourseProfile createCourseProfile(CourseProfile course) {
        log.info("Creating course profile for student: {} course: {}", course.getStudentId(), course.getCourseName());
        return courseProfileRepository.save(course);
    }

    @Transactional(readOnly = true)
    public CourseProfile getCourseProfileById(String id) {
        return courseProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CourseProfile> getCourseProfilesByStudent(String studentId) {
        return courseProfileRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<CourseProfile> getCourseProfilesByType(String courseType) {
        return courseProfileRepository.findByCourseTypeAndIsActiveTrue(courseType);
    }

    @Transactional(readOnly = true)
    public List<CourseProfile> getCourseProfilesByStatus(String courseStatus) {
        return courseProfileRepository.findByCourseStatusAndIsActiveTrue(courseStatus);
    }

    @Transactional(readOnly = true)
    public List<CourseProfile> getCourseProfilesByLevel(String difficultyLevel) {
        return courseProfileRepository.findByDifficultyLevelAndIsActiveTrue(difficultyLevel);
    }

    @Transactional(readOnly = true)
    public List<CourseProfile> getInProgressByStudent(String studentId) {
        return courseProfileRepository.findInProgressByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<CourseProfile> getEnrolledCourses() {
        return courseProfileRepository.findEnrolledCourses();
    }

    @Transactional
    public CourseProfile updateCourseProfile(String id, CourseProfile updated) {
        CourseProfile existing = getCourseProfileById(id);
        existing.setCourseName(updated.getCourseName());
        existing.setCourseType(updated.getCourseType());
        existing.setCourseStatus(updated.getCourseStatus());
        existing.setDifficultyLevel(updated.getDifficultyLevel());
        existing.setDescription(updated.getDescription());
        existing.setLearningObjectives(updated.getLearningObjectives());
        existing.setPrerequisites(updated.getPrerequisites());
        existing.setEstimatedDurationHours(updated.getEstimatedDurationHours());
        existing.setCompletionPercentage(updated.getCompletionPercentage());
        return courseProfileRepository.save(existing);
    }

    @Transactional
    public CourseProfile completeCourseProfile(String id) {
        CourseProfile existing = getCourseProfileById(id);
        existing.setCourseStatus("COMPLETED");
        existing.setCompletionPercentage(100);
        existing.setCompletedAt(LocalDateTime.now());
        return courseProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateCourseProfile(String id) {
        CourseProfile existing = getCourseProfileById(id);
        existing.setActive(false);
        courseProfileRepository.save(existing);
    }
}
