package com.example.EduManagmentSystem.response;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Data
@Setter
public class ClassTimeResponse {
    LocalDate date;
    String startTime;
    int durationTimeInMin;

    public ClassTimeResponse() {
    }

    public ClassTimeResponse(LocalDate date, String startTime, int durationTimeInMin) {
        this.date = date;
        this.startTime = startTime;
        this.durationTimeInMin = durationTimeInMin;
    }
}
