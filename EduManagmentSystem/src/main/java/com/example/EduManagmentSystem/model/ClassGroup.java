package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.ClassType;

public class ClassGroup {
    Long id;
    String groupCode;
    int placeLimit;
    int registeredStudents;
    ClassType classType;
    Long teacherId;
    String courseCode;
}
