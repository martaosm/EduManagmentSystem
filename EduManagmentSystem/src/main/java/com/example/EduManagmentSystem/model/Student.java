package com.example.EduManagmentSystem.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class Student extends Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String indexNumber;
    Date studyStartDate;
    Long accountId;
    String classGroupCode;

}
