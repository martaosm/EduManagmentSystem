package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Entity
public class MandatoryBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String courseGroupCode;
    Long semesterId;

    public MandatoryBlock() {
    }

    public MandatoryBlock(String name, String courseGroupCode, Long semesterId) {
        this.name = name;
        this.courseGroupCode = courseGroupCode;
        this.semesterId = semesterId;
    }
}
