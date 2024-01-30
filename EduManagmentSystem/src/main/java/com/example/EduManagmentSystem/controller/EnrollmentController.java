package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.response.ClassGroupResponse;
import com.example.EduManagmentSystem.response.ClassGroupStudentAssignResponse;
import com.example.EduManagmentSystem.response.CourseResponse;
import com.example.EduManagmentSystem.response.StudyMajorStudentResponse;
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
    public List<CourseResponse> getCoursesForStudent(@RequestParam String studyPlanCode,
                                                     @RequestParam int semesterNumber) throws UnknownHostException {
        return enrollmentService.getCoursesForStudent(studyPlanCode, semesterNumber);
    }

    @GetMapping("/getClassesForCourse")
    public List<ClassGroupResponse> getClassesForCourse(@RequestParam String studyPlanCode,
                                                        @RequestParam int semesterNumber,
                                                        @RequestParam String courseCode) throws Exception {
        return enrollmentService.getClassesForCourses(studyPlanCode, semesterNumber, courseCode);
    }

    @PostMapping("/signForClass")
    public ClassGroupStudentAssignResponse signForClass(@RequestParam String studentIndex,
                                                        @RequestParam String groupCode) throws Exception {
        return enrollmentService.signForClass(studentIndex, groupCode);
    }

    @GetMapping("/findAllClassGroupsWithPlaceLimitReached")
    public List<ClassGroupResponse> findAllClassGroupsWithPlaceLimitReached(){
        return enrollmentService.findAllClassGroupsWithPlaceLimitReached();
    }

    @PutMapping("/increasePlaceLimit")
    public ClassGroupResponse increasePlaceLimit(@RequestParam String groupCode, @RequestParam int newPlaceLimit) throws Exception {
        return enrollmentService.increasePlaceLimit(groupCode, newPlaceLimit);
    }
}
