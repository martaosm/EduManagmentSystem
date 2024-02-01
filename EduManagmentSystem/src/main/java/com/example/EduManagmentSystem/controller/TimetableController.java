package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.request.TeacherRequest;
import com.example.EduManagmentSystem.response.ClassGroupResponse;
import com.example.EduManagmentSystem.response.StudyPlanResponse;
import com.example.EduManagmentSystem.response.TeacherResponse;
import com.example.EduManagmentSystem.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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

    @PostMapping("/setTeacherForClassGroup")
    public ClassGroupResponse setTeacherForClassGroup(@RequestBody TeacherRequest teacherRequest) throws Exception {
        return timetableService.setTeacherIdForClassGroup(teacherRequest.getClassId(), teacherRequest.getLecturerId());
    }

    @GetMapping("/getAllStudyPlans")
    public List<StudyPlanResponse> getAllStudyPlans() throws UnknownHostException {
        return timetableService.getAllStudyPlans();
    }

}
