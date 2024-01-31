package com.example.EduManagmentSystem.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class GradeRequest {
    String studentIndex;
    Double gradeValue;
    String groupCode;
    Long teacherId;
}
