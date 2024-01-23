package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Entity
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int semesterNumber;
    String studyPlanCode;

    public Semester() {
    }

    public Semester(int semesterNumber, String studyPlanCode) {
        this.semesterNumber = semesterNumber;
        this.studyPlanCode = studyPlanCode;
    }
}
