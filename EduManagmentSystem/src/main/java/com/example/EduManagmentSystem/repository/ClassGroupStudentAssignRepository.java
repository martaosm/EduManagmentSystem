package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.ClassGroupStudentAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassGroupStudentAssignRepository extends JpaRepository<ClassGroupStudentAssign, Long> {
    List<ClassGroupStudentAssign>findAllByStudentIndex(String studentIndex);
}
