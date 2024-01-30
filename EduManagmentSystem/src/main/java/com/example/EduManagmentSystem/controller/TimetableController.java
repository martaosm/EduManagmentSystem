package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.response.ClassGroupResponse;
import com.example.EduManagmentSystem.response.StudyPlanResponse;
import com.example.EduManagmentSystem.response.TeacherResponse;
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

    @GetMapping("/getTimetableForStudyPlan")
    public List<ClassGroupResponse> getTimetableForMajor(@RequestParam String studyPlanCode, @RequestParam int semesterNumber) throws UnknownHostException {
        return timetableService.getAllClassGroupsForStudyPlan(studyPlanCode, semesterNumber);
    }

    @GetMapping("/getAllTeachers")
    public List<TeacherResponse> getAllTeachers(){
        return timetableService.getAllTeachers();
    }

    //TODO: request body(lecturerId, classId)
    @PostMapping("/setTeacherForClassGroup")
    public ClassGroupResponse setTeacherForClassGroup(@RequestParam String groupCode, @RequestParam Long teacherId) throws Exception {
        return timetableService.setTeacherIdForClassGroup(groupCode, teacherId);
    }

    @GetMapping("/getAllStudyPlans")
    public List<StudyPlanResponse> getAllStudyPlans() throws UnknownHostException {
        return timetableService.getAllStudyPlans();
    }

}
