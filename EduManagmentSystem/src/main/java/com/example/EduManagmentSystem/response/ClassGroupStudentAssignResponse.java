package com.example.EduManagmentSystem.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClassGroupStudentAssignResponse {
    Long id;
    String studentIndex;
    String groupCode;
    int registeredStudents;
}
