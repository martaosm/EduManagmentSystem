package com.example.EduManagmentSystem.response;

import com.example.EduManagmentSystem.enums.ClassType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseResponse {
    String courseCode;
    String nameInPolish;
    int numberOfPointsECTS;
    int numberOfHoursZZU;
    int numberOfHoursCNPS;
    ClassType classType;

    public CourseResponse() {
    }

    public CourseResponse(String courseCode, String nameInPolish,
                          int numberOfHoursZZU, int numberOfHoursCNPS,
                          int numberOfPointsECTS, ClassType classType) {
        this.courseCode = courseCode;
        this.nameInPolish = nameInPolish;
        this.numberOfHoursZZU = numberOfHoursZZU;
        this.numberOfHoursCNPS = numberOfHoursCNPS;
        this.numberOfPointsECTS = numberOfPointsECTS;
        this.classType = classType;
    }
}
