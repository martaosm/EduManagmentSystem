package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.GradeType;
import com.example.EduManagmentSystem.enums.GradeValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double gradeValue;
    String comment;
    String gradedElement;
    Long gradeTypeId;
    String studentIndex;
    Long teacherId;
    String classGroupCode;

    public Grade(Double gradeValue, String comment, String gradedElement, Long gradeTypeId, String studentIndex, Long teacherId, String classGroupCode) {
        this.gradeValue = gradeValue;
        this.comment = comment;
        this.gradedElement = gradedElement;
        this.gradeTypeId = gradeTypeId;
        this.studentIndex = studentIndex;
        this.teacherId = teacherId;
        this.classGroupCode = classGroupCode;
    }
}
