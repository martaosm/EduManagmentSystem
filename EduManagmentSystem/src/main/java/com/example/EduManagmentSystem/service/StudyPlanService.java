package com.example.EduManagmentSystem.service;

import com.example.EduManagmentSystem.enums.ClassType;
import com.example.EduManagmentSystem.enums.PlanStatus;
import com.example.EduManagmentSystem.model.*;
import com.example.EduManagmentSystem.repository.*;
import com.example.EduManagmentSystem.response.CourseResponse;
import com.example.EduManagmentSystem.response.CoursesListResponse;
import com.example.EduManagmentSystem.response.StudyPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<StudyPlanResponse> getAllStudyPlans() {
        List<StudyPlan> studyPlans = studyPlanRepository.findAll();
        List<StudyPlanResponse> response = new ArrayList<>();
        for(StudyPlan studyPlan : studyPlans){
            response.add(StudyPlanMapper(studyPlan));
        }
        return response;
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
                        CourseMandBlockAssign cmba = new CourseMandBlockAssign();
                        cmba.setMandBlockId(mandatoryBlock.getId());
                        cmba.setCourseCode(courseCode);
                        cmaRepository.save(cmba);
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

    public List<CourseResponse> getAllCoursesAssignedToStudyPlan(String studyPlanCode) throws Exception {
        if (semesterRepository.findByStudyPlanCode(studyPlanCode).isPresent()) {
            Semester semester = semesterRepository.findByStudyPlanCode(studyPlanCode).get();
            if(mandatoryBlockRepository.findBySemesterId(semester.getId()).isPresent()){
                MandatoryBlock mandatoryBlock = mandatoryBlockRepository.findBySemesterId(semester.getId()).get();
                //CoursesListResponse coursesListResponse = new CoursesListResponse();
                List<CourseMandBlockAssign> cms = cmaRepository.findAllByMandBlockId(mandatoryBlock.getId());
                List<CourseResponse> courses = new ArrayList<>();
                for(CourseMandBlockAssign cm : cms){
                    if(courseRepository.findByCourseCode(cm.getCourseCode()).isPresent()){
                        courses.add(CourseMapper(courseRepository.findByCourseCode(cm.getCourseCode()).get()));
                    }else{
                        throw new Exception("Course with this id doesn't exist");
                    }
                }
                //coursesListResponse.setCourseList(courses);
                return courses;
            }else{
                throw new Exception("Mandatory block assigned to this semester doesn't exist");
            }
        } else {
            throw new Exception("Semester with this id doesn't exist");
        }
    }

    public StudyPlanResponse getStudyPlanByMajorCode(String majorCode) throws Exception {
        if (studyPlanRepository.findByMajorCode(majorCode).isPresent()) {
            return StudyPlanMapper(studyPlanRepository.findByMajorCode(majorCode).get());
        } else {
            throw new Exception("Study plan assigned to this major code doesn't exist");
        }
    }

    public StudyPlanResponse StudyPlanMapper(StudyPlan studyPlan){
        StudyMajor studyMajor = studyMajorRepository.getStudyMajorByMajorCode(studyPlan.getMajorCode());
        Semester semester = semesterRepository.findByStudyPlanCode(studyPlan.getStudyPlanCode()).get();
        return new StudyPlanResponse(studyPlan.getStudyPlanCode(), studyMajor.getName(), studyPlan.getEducationLevel(),
                PlanStatus.values()[studyPlan.getPlanStatusId().intValue()-1],semester.getSemesterNumber(),
                studyPlan.getEducationLevel(), studyPlan.getSpecialization());
    }

    public CourseResponse CourseMapper(Course course){
        return new CourseResponse(course.getCourseCode(), course.getNameInPolish(),
                course.getNumberOfHoursZZU(), course.getNumberOfHoursCNPS(), course.getNumberOfPointsECTS(),
                ClassType.values()[course.getClassTypeId().intValue()-1]);
    }
}
