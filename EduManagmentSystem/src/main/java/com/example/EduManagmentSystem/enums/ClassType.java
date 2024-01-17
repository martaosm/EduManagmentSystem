package com.example.EduManagmentSystem.enums;

public enum ClassType {
    LECTURE(1L,"lecture"),
    EXERCISE(2L,"exercise"),
    LABORATORY(3L,"laboratory"),
    PROJECT(4L,"project"),
    SEMINAR(5L,"seminar");

    ClassType(Long id, String name) {
    }
}
