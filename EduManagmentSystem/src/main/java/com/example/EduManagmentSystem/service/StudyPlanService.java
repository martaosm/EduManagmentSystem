package com.example.EduManagmentSystem.service;


import com.example.EduManagmentSystem.enums.ClassType;
import com.example.EduManagmentSystem.enums.PlanStatus;
import com.example.EduManagmentSystem.model.*;
import com.example.EduManagmentSystem.repository.*;
import com.example.EduManagmentSystem.response.AddCourseResponse;
import com.example.EduManagmentSystem.response.CourseResponse;
import com.example.EduManagmentSystem.response.SemesterResponse;
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

    public StudyPlanResponse archiveStudyPlan(String studyPlanCode) {
        if (studyPlanRepository.findByStudyPlanCode(studyPlanCode).isPresent()) {
            StudyPlan studyPlan = studyPlanRepository.findByStudyPlanCode(studyPlanCode).get();
            studyPlan.setPlanStatusId(PlanStatus.ARCHIVAL.getId());
            studyPlanRepository.save(studyPlan);
            return StudyPlanMapper(studyPlan);
        }
        return new StudyPlanResponse();
    }

//    public StudyPlan getStudyPlanById(String studyPlanCode) {
//        if (studyPlanRepository.findByStudyPlanCode(studyPlanCode).isPresent()) {
//            return studyPlanRepository.findByStudyPlanCode(studyPlanCode).get();
//        } else {
//            return new StudyPlan();
//        }
//    }

    public SemesterResponse addCourseToStudyPlan(String studyPlanCode, String courseCode, int semesterNumber) throws Exception {
        if (semesterRepository.findByStudyPlanCodeAndSemesterNumber(studyPlanCode, semesterNumber).isPresent()) {
            Semester semester = semesterRepository.findByStudyPlanCodeAndSemesterNumber(studyPlanCode, semesterNumber).get();
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
            }else{
                throw new Exception("Mandatory block with this id doesn't exist");
            }
        } else {
            throw new Exception("Semester with this id doesn't exist");
        }
        return getAllCoursesAssignedToStudyPlan(studyPlanCode).stream().filter(s -> s.getSemesterNumber() == semesterNumber).toList().get(0);
    }

    public List<AddCourseResponse> getAllCourses(){
        List<AddCourseResponse> response = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();
        for(Course course : courses){
            response.add(new AddCourseResponse(course.getCourseCode(), course.getNameInPolish()));
        }
        return response;
    }


    //TODO: return all courses for study plan
    public List<SemesterResponse> getAllCoursesAssignedToStudyPlan(String studyPlanCode) throws Exception {
        List<Semester> semesters = semesterRepository.findAllByStudyPlanCode(studyPlanCode);
        List<SemesterResponse> responses = new ArrayList<>();

        for(Semester semester : semesters) {
            //if (semesterRepository.findByStudyPlanCodeAndSemesterNumber(studyPlanCode, semesterNumber).isPresent()) {
            //Semester semester = semesterRepository.findByStudyPlanCodeAndSemesterNumber(studyPlanCode, semesterNumber).get();
            if (mandatoryBlockRepository.findBySemesterId(semester.getId()).isPresent()) {
                MandatoryBlock mandatoryBlock = mandatoryBlockRepository.findBySemesterId(semester.getId()).get();
                List<CourseMandBlockAssign> cms = cmaRepository.findAllByMandBlockId(mandatoryBlock.getId());
                List<CourseResponse> courses = new ArrayList<>();
                for (CourseMandBlockAssign cm : cms) {
                    if (courseRepository.findByCourseCode(cm.getCourseCode()).isPresent()) {
                        courses.add(CourseMapper(courseRepository.findByCourseCode(cm.getCourseCode()).get()));
                    } else {
                        throw new Exception("Course with this id doesn't exist");
                    }
                }
                responses.add(new SemesterResponse(semester.getSemesterNumber(), courses));
            } else {
                throw new Exception("Mandatory block assigned to this semester doesn't exist");
            }
        }
        return responses;

    }

    public StudyPlanResponse StudyPlanMapper(StudyPlan studyPlan){
        StudyMajor studyMajor = studyMajorRepository.getStudyMajorByMajorCode(studyPlan.getMajorCode());
        //Semester semester = semesterRepository.findByStudyPlanCodeAndSemesterNumber(studyPlan.getStudyPlanCode(), semesterNumber).get();
        return new StudyPlanResponse(studyPlan.getStudyPlanCode(), studyMajor.getName(), studyMajor.getMajorCode(),
                studyPlan.getEducationLevel(), PlanStatus.values()[studyPlan.getPlanStatusId().intValue()-1],
                studyPlan.getDepartment(), studyPlan.getStudyForm(),
                studyPlan.getEducationLevel(), studyPlan.getSpecialization());
    }

    public CourseResponse CourseMapper(Course course){
        return new CourseResponse(course.getCourseCode(), course.getNameInPolish(),
                course.getNumberOfHoursZZU(), course.getNumberOfHoursCNPS(), course.getNumberOfPointsECTS(),
                ClassType.values()[course.getClassTypeId().intValue()-1]);
    }

}
