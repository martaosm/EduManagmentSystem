package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String indexNumber;
    Date studyStartDate;
    Long accountId;
    String classGroupCode;

}
