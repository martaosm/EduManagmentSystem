package com.example.EduManagmentSystem.request;

import com.example.EduManagmentSystem.enums.GradeType;
import lombok.Data;

@Data
public class GradeRequest {
    Double gradeValue;
    String comment;
    String gradedElement;
    GradeType gradeType;
    String studentIndex;
    Long teacherId;
    String classGroupCode;
}
