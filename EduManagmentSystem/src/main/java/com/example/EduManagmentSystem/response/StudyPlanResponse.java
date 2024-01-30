package com.example.EduManagmentSystem.response;

import com.example.EduManagmentSystem.enums.PlanStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudyPlanResponse {
    String studyPlanCode;
    String inMotionSinceEduCycle;
    PlanStatus planStatus;
    String majorName;
    String majorCode;
    String faculty;
    String educationLevel;
    String studyMode;
    String specialization;
}
