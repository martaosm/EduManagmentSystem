package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Setter
@Getter
@Entity
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String indexNumber;
    LocalDate studyStartDate;
    Long accountId;

}
