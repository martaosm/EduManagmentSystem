package com.example.EduManagmentSystem.response;

import com.example.EduManagmentSystem.enums.ClassType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ClassGroupResponse {

    String groupCode;
    String courseName;
    ClassType classType;
    TeacherResponse teacherResponse;
    String courseCode;
    String classDate;
    int registeredStudents;
    int placeLimit;

    public ClassGroupResponse() {
    }

    public ClassGroupResponse(String groupCode, String courseName, int placeLimit, int registeredStudents, ClassType classType, TeacherResponse teacherResponse, String courseCode) {
        this.groupCode = groupCode;
        this. courseName = courseName;
        this.placeLimit = placeLimit;
        this.registeredStudents = registeredStudents;
        this.classType = classType;
        this.teacherResponse = teacherResponse;
        this.courseCode = courseCode;
    }
}
