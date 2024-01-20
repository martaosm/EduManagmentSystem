package com.example.EduManagmentSystem.enums;

public enum PlanStatus {
    ACTIVE(1L,"active"),
    ARCHIVAL(2L,"archival");

    private Long id;
    private String name;

    PlanStatus(Long id, String name) {
        this.id=id;
        this.name=name;
    }

    public Long getId(){
        return this.id;
    }
}
