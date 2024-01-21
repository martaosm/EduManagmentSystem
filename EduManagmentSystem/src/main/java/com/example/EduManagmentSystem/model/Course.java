package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.ClassType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String courseCode;
    String nameInPolish;
    String nameInEnglish;
    int numberOfHoursZZU;
    int numberOfHoursCNPS;
    int numberOfHoursECTS;
    Long classTypeId;
    Long selectableBlockId;
    Long mandatoryBlockId;
    Long courseGroupId;
}
