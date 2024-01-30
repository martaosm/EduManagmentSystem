package com.example.EduManagmentSystem.model;

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
public class ClassGroupCourseAssign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String groupCode;
    String courseCode;
}
