package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
public class StudyMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String majorCode;
    String name;
    String description;
}
