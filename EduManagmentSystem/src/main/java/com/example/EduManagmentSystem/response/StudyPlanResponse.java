package com.example.EduManagmentSystem.response;


import com.example.EduManagmentSystem.enums.PlanStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    public StudyPlanResponse() {
    }

    public StudyPlanResponse(String studyPlanCode, String majorName, String majorCode,
                             String inMotionSinceEduCycle, PlanStatus planStatus,
                             String educationLevel, String specialization) {
        this.studyPlanCode = studyPlanCode;
        this.majorName = majorName;
        this.majorCode = majorCode;
        this.inMotionSinceEduCycle = inMotionSinceEduCycle;
        this.planStatus = planStatus;
        //this.semesterNumber = semesterNumber;
        this.educationLevel = educationLevel;
        this.specialization = specialization;
    }
}