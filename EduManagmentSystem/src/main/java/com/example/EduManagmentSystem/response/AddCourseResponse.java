package com.example.EduManagmentSystem.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddCourseResponse {
    String courseCode;
    String courseNameInPolish;

    public AddCourseResponse() {
    }

    public AddCourseResponse(String courseCode, String courseNameInPolish) {
        this.courseCode = courseCode;
        this.courseNameInPolish = courseNameInPolish;
    }
}
