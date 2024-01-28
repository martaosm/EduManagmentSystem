package com.example.EduManagmentSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class CourseMandBlockAssign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long mandBlockId;
    String courseCode;

    public CourseMandBlockAssign() {
    }
    public CourseMandBlockAssign(Long mandBlockId, String courseCode) {
        this.mandBlockId = mandBlockId;
        this.courseCode = courseCode;
    }
}
