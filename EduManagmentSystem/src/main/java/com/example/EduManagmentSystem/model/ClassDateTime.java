package com.example.EduManagmentSystem.model;

import jakarta.persistence.Id;

import java.util.Date;

public class ClassDateTime {

    @Id
    Long id;
    Date date;
    String startTime;
    int durationTimeInMin;
    String groupCode;
}
