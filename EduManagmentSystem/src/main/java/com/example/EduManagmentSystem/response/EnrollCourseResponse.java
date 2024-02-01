package com.example.EduManagmentSystem.response;

import com.example.EduManagmentSystem.enums.ClassType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EnrollCourseResponse {
    String courseCode;
    String courseNameInPolish;
    ClassType classType;
    Boolean isStudentEnrolled;
}
