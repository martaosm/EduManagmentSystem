package com.example.EduManagmentSystem.service;

import com.example.EduManagmentSystem.enums.PlanStatus;
import com.example.EduManagmentSystem.model.StudyPlan;
import com.example.EduManagmentSystem.repository.StudyPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyPlanService {

    @Autowired
    StudyPlanRepository studyPlanRepository;

    public List<StudyPlan> getAllStudyPlans(){
        return studyPlanRepository.findAll();
    }

    public void archiveStudyPlan(Long studyPlanId){
        if(studyPlanRepository.findById(studyPlanId).isPresent()){
            StudyPlan studyPlan = studyPlanRepository.findById(studyPlanId).get();
            studyPlan.setPlanStatusId(PlanStatus.ARCHIVAL.getId());
            studyPlanRepository.save(studyPlan);
        }
    }

    public void addCourseToStudyPlan(String )
}
