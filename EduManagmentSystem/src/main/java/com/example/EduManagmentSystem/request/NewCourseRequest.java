package com.example.EduManagmentSystem.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class NewCourseRequest {
    String courseId;
    String studyPlanId;
    int semester;
}
