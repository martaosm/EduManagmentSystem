package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClassPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String educationCycle;
    String majorCode;
    String classGroupCode;
}
