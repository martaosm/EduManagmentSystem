package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.ClassType;
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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String courseCode;
    String nameInPolish;
    String nameInEnglish;
    int numberOfHoursZZU;
    int numberOfHoursCNPS;
    int numberOfHoursECTS;
    Long classTypeId;
    Long selectableBlockId;
    Long mandatoryBlockId;
    Long courseGroupId;

    public Course() {
    }

    public Course(String nameInPolish, String nameInEnglish, int numberOfHoursZZU, int numberOfHoursCNPS, int numberOfHoursECTS, Long classTypeId) {
        this.nameInPolish = nameInPolish;
        this.nameInEnglish = nameInEnglish;
        this.numberOfHoursZZU = numberOfHoursZZU;
        this.numberOfHoursCNPS = numberOfHoursCNPS;
        this.numberOfHoursECTS = numberOfHoursECTS;
        this.classTypeId = classTypeId;
    }
}
