package com.example.EduManagmentSystem.response;


public class TeacherResponse {
    Long id;
    String firstName;
    String lastName;
    String academicTitle;

    public TeacherResponse() {
    }

    public TeacherResponse(Long id, String firstName, String lastName, String academicTitle) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicTitle = academicTitle;
    }
}
