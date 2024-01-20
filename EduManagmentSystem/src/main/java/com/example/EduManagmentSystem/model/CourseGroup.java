package com.example.EduManagmentSystem.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CourseGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String courseGroupCode;
    String name;
}
