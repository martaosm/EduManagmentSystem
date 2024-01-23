package com.example.EduManagmentSystem.service;

import com.example.EduManagmentSystem.enums.PlanStatus;
import com.example.EduManagmentSystem.model.*;
import com.example.EduManagmentSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyPlanService {

    @Autowired
    StudyPlanRepository studyPlanRepository;

    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    MandatoryBlockRepository mandatoryBlockRepository;
    @Autowired
    CourseMandBlockAssignRepository cmaRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudyMajorRepository studyMajorRepository;


    public List<StudyPlan> getAllStudyPlans() {
        return studyPlanRepository.findAll();
    }

    public void archiveStudyPlan(String studyPlanCode) {
        if (studyPlanRepository.findByStudyPlanCode(studyPlanCode).isPresent()) {
            StudyPlan studyPlan = studyPlanRepository.findByStudyPlanCode(studyPlanCode).get();
            studyPlan.setPlanStatusId(PlanStatus.ARCHIVAL.getId());
            studyPlanRepository.save(studyPlan);
        }
    }

    public StudyPlan getStudyPlanById(String studyPlanCode) {
        if (studyPlanRepository.findByStudyPlanCode(studyPlanCode).isPresent()) {
            return studyPlanRepository.findByStudyPlanCode(studyPlanCode).get();
        } else {
            return new StudyPlan();
        }
    }

    public void addCourseToStudyPlan(String studyPlanCode, String courseCode) throws Exception {
        if (semesterRepository.findByStudyPlanCode(studyPlanCode).isPresent()) {
            Semester semester = semesterRepository.findByStudyPlanCode(studyPlanCode).get();
            if (mandatoryBlockRepository.findBySemesterId(semester.getId()).isPresent()) {
                MandatoryBlock mandatoryBlock = mandatoryBlockRepository.findBySemesterId(semester.getId()).get();
                if (courseRepository.findByCourseCode(courseCode).isPresent()) {
                    if (cmaRepository.findByMandBlockIdAndCourseCode(mandatoryBlock.getId(), courseCode).isEmpty()) {
                        cmaRepository.save(new CourseMandBlockAssign(mandatoryBlock.getId(), courseCode));
                        Course course = courseRepository.findByCourseCode(courseCode).get();
                        course.setMandatoryBlockId(mandatoryBlock.getId());
                        courseRepository.save(course);
                    } else {
                        throw new Exception("Course is already assigned to this study plan");
                    }
                } else {
                    throw new Exception("Course with this code doesn't exist");
                }
                cmaRepository.findByMandBlockIdAndCourseCode(mandatoryBlock.getId(), courseCode).get();
            }else{
                throw new Exception("Mandatory block with this id doesn't exist");
            }
        } else {
            throw new Exception("Semester with this id doesn't exist");
        }
    }

    public List<Course> getAllCoursesAssignedToStudyPlan(String studyPlanCode) throws Exception {
        if (semesterRepository.findByStudyPlanCode(studyPlanCode).isPresent()) {
            Semester semester = semesterRepository.findByStudyPlanCode(studyPlanCode).get();
            MandatoryBlock mandatoryBlock = mandatoryBlockRepository.findBySemesterId(semester.getId()).get();
            return courseRepository.findAllByMandatoryBlockId(mandatoryBlock.getId());
        } else {
            throw new Exception("Semester with this id doesn't exist");
        }
    }
}
