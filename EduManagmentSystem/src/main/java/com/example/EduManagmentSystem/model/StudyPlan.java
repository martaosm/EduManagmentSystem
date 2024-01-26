package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.PlanStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
public class StudyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
