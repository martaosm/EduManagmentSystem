package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.model.StudyPlan;
import com.example.EduManagmentSystem.service.StudyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
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
}
