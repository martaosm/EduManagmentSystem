package com.example.EduManagmentSystem.enums;

public enum ClassType {
    LECTURE(1L,"Wykład"),
    EXERCISE(2L,"Ćwiczenia"),
    LABORATORY(3L,"Laboratorium"),
    PROJECT(4L,"Projekt"),
    SEMINAR(5L,"Seminarium");

    private Long id;
    private String name;

    ClassType(Long id, String name) {
    }

    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
}
