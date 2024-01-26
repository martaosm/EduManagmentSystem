package com.example.EduManagmentSystem.response;

import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
public class ClassTimeResponse {
    Date date;
    String startTime;
    int durationTimeInMin;

    public ClassTimeResponse() {
    }

    public ClassTimeResponse(Date date, String startTime, int durationTimeInMin) {
        this.date = date;
        this.startTime = startTime;
        this.durationTimeInMin = durationTimeInMin;
    }
}
