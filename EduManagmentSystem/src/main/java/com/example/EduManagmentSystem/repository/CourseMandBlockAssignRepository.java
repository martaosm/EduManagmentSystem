package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.CourseMandBlockAssign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseMandBlockAssignRepository extends JpaRepository<CourseMandBlockAssign, Long> {
    CourseMandBlockAssign findByCourseCode(String courseCode);
    Optional<CourseMandBlockAssign> findByMandBlockIdAndCourseCode(Long mandBlockId, String courseCode);
}
