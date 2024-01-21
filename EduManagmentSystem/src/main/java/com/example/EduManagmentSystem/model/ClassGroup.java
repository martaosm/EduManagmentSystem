package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.ClassType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String groupCode;
    int placeLimit;
    int registeredStudents;
    ClassType classType;
    Long teacherId;
    String courseCode;
}
