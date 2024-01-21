package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourseGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String courseGroupCode;
    String name;
}
