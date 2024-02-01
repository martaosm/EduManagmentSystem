package com.example.EduManagmentSystem.response;

import com.example.EduManagmentSystem.enums.ClassType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClassGroupTeacherResponse {
    String groupCode;
    String courseName;
    ClassType classType;
    String courseCode;
    String classDate;
    int registeredStudent;
}
