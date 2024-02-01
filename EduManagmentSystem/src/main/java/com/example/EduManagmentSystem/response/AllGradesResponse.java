package com.example.EduManagmentSystem.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class AllGradesResponse {
    int semesterNumber;
    List<GradeResponse> grades;
}
