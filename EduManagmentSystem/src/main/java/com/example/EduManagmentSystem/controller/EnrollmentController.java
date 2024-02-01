package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.request.NewPlaceLimitRequest;
import com.example.EduManagmentSystem.request.StudentSignUpRequest;
import com.example.EduManagmentSystem.response.*;
import com.example.EduManagmentSystem.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    @GetMapping("/getAllMajorsForStudent")
    public List<StudyMajorStudentResponse> getAllMajorsForStudent(@RequestParam String studentIndex){
        return enrollmentService.getAllMajorsForStudent(studentIndex);
    }

    @GetMapping("/getCoursesForStudent")
    public List<EnrollCourseResponse> getCoursesForStudent(@RequestParam String studentIndex) throws UnknownHostException {
        List<StudyMajorStudentResponse> studentMajor = enrollmentService.getAllMajorsForStudent(studentIndex);
        return enrollmentService.getCoursesForStudent(studentMajor.get(0).getStudyPlanCode(), studentMajor.get(0).getSemesterNumber());
    }

    @GetMapping("/getClassesForCourse")
    public List<ClassGroupResponse> getClassesForCourse(@RequestParam String studyPlanCode,
                                                        @RequestParam int semesterNumber,
                                                        @RequestParam String courseCode) throws Exception {
        return enrollmentService.getClassesForCourses(studyPlanCode, semesterNumber, courseCode);
    }

    @PostMapping("/signForClass")
    public ClassGroupStudentAssignResponse signForClass(@RequestBody StudentSignUpRequest request) throws Exception {
        return enrollmentService.signForClass(request.getStudentIndex(), request.getClassId());
    }

//    @GetMapping("/findAllClassGroupsWithPlaceLimitReached")
//    public List<ClassGroupResponse> findAllClassGroupsWithPlaceLimitReached(){
//        return enrollmentService.findAllClassGroupsWithPlaceLimitReached();
//    }

    @PutMapping("/increasePlaceLimit")
    public ClassGroupResponse increasePlaceLimit(@RequestBody NewPlaceLimitRequest request) throws Exception {
        return enrollmentService.increasePlaceLimit(request.getClassId(), request.getNewClassPlaceLimit());
    }
}
