package com.example.EduManagmentSystem.enums;


public enum ClassType {
    LECTURE(1L,"Wykład"),
    EXERCISE(2L,"Ćwiczenia"),
    LABORATORY(3L,"Laboratorium"),
    PROJECT(4L,"Projekt"),
    SEMINAR(5L,"Seminarium");

    ClassType(Long id, String name) {
    }

}
