package com.example.EduManagmentSystem.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewPlaceLimitRequest {
    int newClassPlaceLimit;
    String classId;
}
