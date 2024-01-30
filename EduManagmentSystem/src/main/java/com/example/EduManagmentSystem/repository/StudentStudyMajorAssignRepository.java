package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.StudentStudyMajorAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentStudyMajorAssignRepository extends JpaRepository<StudentStudyMajorAssign, Long> {
    List<StudentStudyMajorAssign> findAllByStudentIndex(String studentIndex);
}
