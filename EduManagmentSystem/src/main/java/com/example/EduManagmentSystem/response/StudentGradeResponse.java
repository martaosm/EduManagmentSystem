package com.example.EduManagmentSystem.response;

import com.example.EduManagmentSystem.enums.GradeValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentGradeResponse {
    String firstName;
    String lastName;
    String index;
    Long gradeId;
    GradeValue gradeValue;
}
