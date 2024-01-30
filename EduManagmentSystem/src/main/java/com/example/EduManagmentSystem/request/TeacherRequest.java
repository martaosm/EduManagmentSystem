package com.example.EduManagmentSystem.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TeacherRequest {
    Long lecturerId;
    String classId;
}
