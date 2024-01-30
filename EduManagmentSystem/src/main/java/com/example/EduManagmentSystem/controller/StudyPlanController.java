package com.example.EduManagmentSystem.controller;


import com.example.EduManagmentSystem.repository.SemesterRepository;
import com.example.EduManagmentSystem.response.AddCourseResponse;
import com.example.EduManagmentSystem.response.CourseResponse;
import com.example.EduManagmentSystem.response.SemesterResponse;
import com.example.EduManagmentSystem.response.StudyPlanResponse;
import com.example.EduManagmentSystem.service.StudyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyPlanController {


    @Autowired
    StudyPlanService studyPlanService;

    @GetMapping("/getAllStudyPlans")
    public List<StudyPlanResponse> getAllStudyPlans(){
        return studyPlanService.getAllStudyPlans();
    }


    @PutMapping("/archiveStudyPlan")
    public StudyPlanResponse archiveStudyPlan(@RequestParam String studyPlanCode){
        return studyPlanService.archiveStudyPlan(studyPlanCode);
    }

    @PostMapping("/addCourseToStudyPlan")
    public SemesterResponse addCourseToStudyPlan(@RequestParam String studyPlanCode, @RequestParam String courseCode, @RequestParam int semesterNumber) throws Exception {
        return studyPlanService.addCourseToStudyPlan(studyPlanCode, courseCode, semesterNumber);
    }


    @GetMapping("/getAllCoursesStudyPlan")
    public List<SemesterResponse> getAllCoursesStudyPlan(@RequestParam String studyPlanCode) throws Exception {
        return studyPlanService.getAllCoursesAssignedToStudyPlan(studyPlanCode);
    }

    @GetMapping("/getAllCourses")
    public List<AddCourseResponse> getAllCourses(){
        return studyPlanService.getAllCourses();
    }

}
