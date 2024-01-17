package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.ClassType;

public class Course {
    Long id;
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
