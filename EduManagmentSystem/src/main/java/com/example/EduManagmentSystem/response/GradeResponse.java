package com.example.EduManagmentSystem.response;

import com.example.EduManagmentSystem.enums.ClassType;
import com.example.EduManagmentSystem.enums.GradeValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GradeResponse {
    Double gradeValue;
    String courseName;
    ClassType classType;
}
