package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    List<Semester> findAllByStudyPlanCode(String studyPlanCode);
    Optional<Semester> findByStudyPlanCodeAndSemesterNumber(String studyPlanCode, int semesterNumber);

}
