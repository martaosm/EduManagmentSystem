package com.example.EduManagmentSystem.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudyMajorStudentResponse {
    Long id;
    String majorCode;
    String name;
    int semesterNumber;
    String studyPlanCode;
}
