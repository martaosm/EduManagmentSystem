package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.GradeType;
import com.example.EduManagmentSystem.enums.GradeValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double gradeValue;
    String comment;
    String gradedElement;
    Long gradeTypeId;
    String studentIndex;
    Long teacherId;
    String classGroupCode;
}
