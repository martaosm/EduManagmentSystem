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
    int placeLimit;
    int registeredStudents;
    ClassType classType;
    Long teacherId;
    String courseCode;
    List<ClassTimeResponse> classTimes;

    public ClassGroupResponse() {
    }

    public ClassGroupResponse(String groupCode, int placeLimit, int registeredStudents, ClassType classType, Long teacherId, String courseCode) {
        this.groupCode = groupCode;
        this.placeLimit = placeLimit;
        this.registeredStudents = registeredStudents;
        this.classType = classType;
        this.teacherId = teacherId;
        this.courseCode = courseCode;
    }
}
