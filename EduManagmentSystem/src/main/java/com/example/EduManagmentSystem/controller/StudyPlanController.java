package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.response.CourseResponse;
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
    public List<CourseResponse> addCourseToStudyPlan(@RequestParam String studyPlanCode, @RequestParam String courseCode, @RequestParam int semesterNumber) throws Exception {
        return studyPlanService.addCourseToStudyPlan(studyPlanCode, courseCode, semesterNumber);
    }

    @GetMapping("/getAllCoursesStudyPlan")
    public List<CourseResponse> getAllCoursesStudyPlan(@RequestParam String studyPlanCode, @RequestParam int semesterNumber) throws Exception {
        return studyPlanService.getAllCoursesAssignedToStudyPlan(studyPlanCode, semesterNumber);
    }

//    @GetMapping("/getStudyPlanByMajorCode")
//    public StudyPlanResponse getStudyPlanByMajorCode(@RequestParam String majorCode) throws Exception {
//        return  studyPlanService.getStudyPlanByMajorCode(majorCode);
//    }
}
