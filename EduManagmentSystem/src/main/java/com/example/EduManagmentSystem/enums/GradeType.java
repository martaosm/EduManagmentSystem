package com.example.EduManagmentSystem.enums;

public enum GradeType {
    SEMESTER(1L,"semester"),  //SEMESTRALNA
    PARTIAL(2L,"partial")  ;//CZASTKOWA


    private Long id;
    private String name;

    GradeType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId(){
        return this.id;
    }

}
