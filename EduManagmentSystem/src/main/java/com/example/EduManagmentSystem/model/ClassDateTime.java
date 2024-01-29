package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Entity
public class ClassDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate date;
    String startTime;
    int durationTimeInMin;
    String groupCode;
}
