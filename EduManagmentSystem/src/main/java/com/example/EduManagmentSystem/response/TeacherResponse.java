package com.example.EduManagmentSystem.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class TeacherResponse{
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
