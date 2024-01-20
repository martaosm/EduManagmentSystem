package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.ClassType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String groupCode;
    int placeLimit;
    int registeredStudents;
    ClassType classType;
    Long teacherId;
    String courseCode;
}
