package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.model.Course;
import com.example.EduManagmentSystem.model.StudyPlan;
import com.example.EduManagmentSystem.response.ClassGroupResponse;
import com.example.EduManagmentSystem.response.CourseResponse;
import com.example.EduManagmentSystem.response.StudyPlanResponse;
import com.example.EduManagmentSystem.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.List;

@RestController
public class TimetableController {

    @Autowired
    TimetableService timetableService;

    @GetMapping("/getTimetableForStudent")
    public List<ClassGroupResponse> getTimetable(@RequestParam String studentIndex) throws Exception {
        return timetableService.getAllClassGroupsForStudent(studentIndex);
    }

    @GetMapping("/getTimetableForMajor")
    public List<ClassGroupResponse> getTimetableForMajor(@RequestParam String majorCode) throws Exception {
        return timetableService.getAllClassGroupsForMajor(majorCode);
    }

    @PostMapping("/setTeacherForClassGroup")
    public void setTeacherForClassGroup(@RequestParam String groupCode, @RequestParam Long teacherId) throws Exception {
        timetableService.setTeacherIdForClassGroup(groupCode, teacherId);
    }

    @GetMapping("getAllStudyPlans")
    public List<StudyPlanResponse> getAllStudyPlans(){

    }

    @GetMapping("/getCoursesAssignedToStudyPlan")
    public List<CourseResponse> getCoursesAssignedToStudyPlan(@RequestParam String majorCode) throws UnknownHostException {
        return timetableService.getCoursesAssignedToStudyPlan(majorCode);
    }
}
