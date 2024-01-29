package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.ClassPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassPlanRepository extends JpaRepository<ClassPlan, Long> {
    List<ClassPlan> findAllByMajorCode(String majorCode);
    List<ClassPlan> findByClassGroupCode(String classGroupCode);
}
