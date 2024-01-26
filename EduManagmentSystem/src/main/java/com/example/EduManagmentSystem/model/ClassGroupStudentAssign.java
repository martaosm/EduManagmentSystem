package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Entity
public class ClassGroupStudentAssign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String studentIndex;
    String groupCode;
}
