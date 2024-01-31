package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findByStudentIndex(String studentIndex);
}
