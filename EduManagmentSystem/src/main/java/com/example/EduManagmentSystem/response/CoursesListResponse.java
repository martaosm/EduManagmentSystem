package com.example.EduManagmentSystem.response;

import com.example.EduManagmentSystem.model.Course;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CoursesListResponse {
    List<Course> courseList;
}
