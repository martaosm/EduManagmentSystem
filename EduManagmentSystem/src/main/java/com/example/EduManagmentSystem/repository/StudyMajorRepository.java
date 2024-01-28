package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.StudyMajor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMajorRepository extends JpaRepository<StudyMajor, String> {
    StudyMajor getStudyMajorByMajorCode(String majorCode);
}
