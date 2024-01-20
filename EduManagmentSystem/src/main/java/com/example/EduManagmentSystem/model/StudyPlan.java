package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.PlanStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class StudyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String studyPlanCode;
    String department;
    String educationLevel;
    String studyForm;
    String studyProfile;
    String specialization;
    String studyLanguage;
    String inMotionSinceEduCycle;
    Long planStatusId;
    String majorCode;
}
