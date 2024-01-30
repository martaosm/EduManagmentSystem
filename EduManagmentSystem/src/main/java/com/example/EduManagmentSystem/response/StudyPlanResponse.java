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
    String majorName;
    String majorCode;
    String inMotionSinceEduCycle;
    PlanStatus planStatus;
    //int semesterNumber;
    String educationLevel;
    String specialization;
}
