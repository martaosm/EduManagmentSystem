package com.example.EduManagmentSystem.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class StudyMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String majorCode;
    String name;
    String description;
}
