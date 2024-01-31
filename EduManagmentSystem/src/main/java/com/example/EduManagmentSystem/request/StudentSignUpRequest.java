package com.example.EduManagmentSystem.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentSignUpRequest {
    String studentIndex;
    String classId;
}
