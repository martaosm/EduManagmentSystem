package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.model.Course;
import com.example.EduManagmentSystem.model.CourseMandBlockAssign;
import com.example.EduManagmentSystem.model.StudyPlan;
import com.example.EduManagmentSystem.service.StudyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyPlanController {

    @Autowired
    StudyPlanService studyPlanService;

    @GetMapping("/getAllStudyPlans")
    public List<StudyPlan> getAllStudyPlans(){
        return studyPlanService.getAllStudyPlans();
    }

    @PutMapping("/archiveStudyPlan")
    public void archiveStudyPlan(@RequestParam String studyPlanCode){
        studyPlanService.archiveStudyPlan(studyPlanCode);
    }

    @PostMapping("/addCourseToStudyPlan")
    public void addCourseToStudyPlan(@RequestParam String studyPlanCode, @RequestParam String courseCode) throws Exception {
        studyPlanService.addCourseToStudyPlan(studyPlanCode, courseCode);
    }

    @GetMapping("/getAllCoursesStudyPlan")
    public List<Course> getAllCoursesStudyPlan(@RequestParam String studyPlanCode) throws Exception {
        return studyPlanService.getAllCoursesAssignedToStudyPlan(studyPlanCode);
    }

    @GetMapping("/getStudyPlanByMajorCode")
    public StudyPlan getStudyPlanByMajorCode(@RequestParam String majorCode) throws Exception {
        return  studyPlanService.getStudyPlanByMajorCode(majorCode);
    }
}
