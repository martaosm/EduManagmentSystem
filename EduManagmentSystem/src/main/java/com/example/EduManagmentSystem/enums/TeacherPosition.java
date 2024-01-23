package com.example.EduManagmentSystem.enums;

public enum TeacherPosition {
    PROFESSOR(1L, "professor"),
    UNI_PROFESSOR(2L, "uni_professor"),
    VISITING_PROFESSOR(3L, "visiting_professor"),
    ADIUNKT(4L, "adiunkt"),
    ASSISTANT(5L, "assistant"),
    PHD_STUDENT(6L, "phd_student"),
    DOCENT(7L, "docent"),
    SENIOR_LECTURER(8L, "senior_lecturer"),
    LECTURER(9L, "lecturer"),
    VISITING_LECTURER(10L, "visiting_lecturer"),
    LECTOR(11L, "lector"),
    INSTRUCTOR(12L, "instructor");


    TeacherPosition(Long id, String name) {
    }
    
}
