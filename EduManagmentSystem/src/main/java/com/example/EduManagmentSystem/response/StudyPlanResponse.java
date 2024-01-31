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
<<<<<<< Updated upstream
}
=======

    public StudyPlanResponse() {
    }

    public StudyPlanResponse(String studyPlanCode, String majorName, String majorCode,
                             String inMotionSinceEduCycle, PlanStatus planStatus, String faculty,
                             String educationLevel, String studyMode, String specialization) {
        this.studyPlanCode = studyPlanCode;
        this.majorName = majorName;
        this.majorCode = majorCode;
        this.inMotionSinceEduCycle = inMotionSinceEduCycle;
        this.planStatus = planStatus;
        this.faculty = faculty;
        this.educationLevel = educationLevel;
        this.studyMode = studyMode;
        this.specialization = specialization;
    }
}
>>>>>>> Stashed changes
