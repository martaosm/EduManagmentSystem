package com.example.EduManagmentSystem.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class SemesterResponse {
    int semesterNumber;
    List<CourseResponse> courses;

    public SemesterResponse() {
    }

    public SemesterResponse(int semesterNumber, List<CourseResponse> courses) {
        this.semesterNumber = semesterNumber;
        this.courses = courses;
    }
}
