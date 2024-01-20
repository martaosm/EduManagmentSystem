package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.AccountType;

import java.util.Date;

public class Student extends Account{
    Long id;
    String indexNumber;
    Date studyStartDate;
    Long accountId;
    String classGroupCode;

    public Student(String username, AccountType accountType) {
        super(username, accountType);
    }
}
