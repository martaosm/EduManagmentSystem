package com.example.EduManagmentSystem.service;

import com.example.EduManagmentSystem.enums.ClassType;
import com.example.EduManagmentSystem.model.*;
import com.example.EduManagmentSystem.repository.*;
import com.example.EduManagmentSystem.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

//@Service
public class EnrollmentService {
//
//    @Autowired
//    StudentStudyMajorAssignRepository studentStudyMajorAssignRepository;
//
//    @Autowired
//    StudyPlanRepository studyPlanRepository;
//
//    @Autowired
//    StudyMajorRepository studyMajorRepository;
//
//    @Autowired
//    ClassGroupStudentAssignRepository classGroupStudentAssignRepository;
//
//    @Autowired
//    ClassGroupRepository classGroupRepository;
//
//    @Autowired
//    ClassDateTimeRepository classDateTimeRepository;
//
//    @Autowired
//    CourseRepository courseRepository;
//
//
//    public List<StudyMajorStudentResponse> getAllMajorsForStudent(String studentIndex){
//        List<StudyMajorStudentResponse> response = new ArrayList<>();
//        List<StudentStudyMajorAssign> majors = studentStudyMajorAssignRepository.findAllByStudentIndex(studentIndex);
//        for(StudentStudyMajorAssign s : majors){
//            response.add(StudentStudyMajorResponseMapper(s));
//        }
//        return response;
//    }
//
//    public List<SemesterResponse> getCoursesForStudent(String studyPlanCode) throws UnknownHostException {
//        //url/port do zmiany
//
//        final String HOSTNAME = InetAddress.getLocalHost().getHostName();
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("studyPlanCode", studyPlanCode);
//
//        ResponseEntity<List<SemesterResponse>> courses
//                = new RestTemplate().exchange(
//                "http://".concat(HOSTNAME).concat(":8081/getAllCoursesStudyPlan?studyPlanCode={studyPlanCode}"),
//                HttpMethod.GET, null,
//                new ParameterizedTypeReference<>(){},
//                params);
//
//        return courses.getBody();
//    }
//
//    public List<ClassGroupResponse> getClassesForCourses(String studyPlanCode, int semesterNumber, String courseCode) throws Exception {
//        //url/port do zmiany
//
//        final String HOSTNAME = InetAddress.getLocalHost().getHostName();
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("studyPlanCode", studyPlanCode);
//        params.put("semesterNumber", semesterNumber);
//
//        ResponseEntity<List<ClassGroupResponse>> classes
//                = new RestTemplate().exchange(
//                "http://".concat(HOSTNAME).concat(":8082/getTimetableForStudyPlan?studyPlanCode={studyPlanCode}&semesterNumber={semesterNumber}"),
//                HttpMethod.GET, null,
//                new ParameterizedTypeReference<>(){},
//                params);
//
//        List<ClassGroupResponse> response = new ArrayList<>();
//        if(classes.getBody() != null){
//            for(ClassGroupResponse cgr : classes.getBody()){
//                if(cgr.getCourseCode().equals(courseCode)){
//                    response.add(cgr);
//                }
//            }
//        }else{
//            throw new Exception("No classes found for this study plan");
//        }
//        return response;
//    }
//
//    public ClassGroupStudentAssignResponse signForClass(String studentIndex, String groupCode) throws Exception {
//        if(classGroupStudentAssignRepository.findByStudentIndexAndGroupCode(studentIndex, groupCode) == null){
//            if(classGroupRepository.findById(groupCode).isPresent()){
//                ClassGroup classGroup = classGroupRepository.findById(groupCode).get();
//                if(classGroup.getRegisteredStudents()<classGroup.getPlaceLimit()){
//                    ClassGroupStudentAssign classGroupStudentAssign = new ClassGroupStudentAssign();
//                    classGroupStudentAssign.setStudentIndex(studentIndex);
//                    classGroupStudentAssign.setGroupCode(groupCode);
//                    classGroupStudentAssignRepository.save(classGroupStudentAssign);
//                    classGroup.setRegisteredStudents(classGroup.getRegisteredStudents()+1);
//                    classGroupRepository.save(classGroup);
//                    return ClassGroupStudentAssignResponseMapper(classGroupStudentAssignRepository.findByStudentIndexAndGroupCode(studentIndex, groupCode));
//                }else{
//                    throw new Exception("Place limit was reached. Student can't sign up for this class");
//                }
//            }else{
//                throw new Exception("Class with this code doesn't exist");
//            }
//        }else{
//            throw new Exception("Student is already signed up for this class");
//        }
//    }
//
//    public List<ClassGroupResponse> findAllClassGroupsWithPlaceLimitReached(){
//        List<ClassGroupResponse> response = new ArrayList<>();
//        List<ClassGroup> classGroups = classGroupRepository.findAll();
//        for(ClassGroup cg : classGroups){
//            if(cg.getRegisteredStudents() == cg.getPlaceLimit()){
//                response.add(ClassGroupResponseMapper(cg));
//            }
//        }
//        return response;
//    }
//
//    public ClassGroupResponse increasePlaceLimit(String groupCode, int newPlaceLimit) throws Exception {
//        if(classGroupRepository.findById(groupCode).isPresent()){
//            ClassGroup classGroup = classGroupRepository.findById(groupCode).get();
//            classGroup.setPlaceLimit(newPlaceLimit);
//            classGroupRepository.save(classGroup);
//            return ClassGroupResponseMapper(classGroup);
//        }else{
//            throw new Exception("Class group with this code doesn't exist");
//        }
//
//    }
//
//    public StudyMajorStudentResponse StudentStudyMajorResponseMapper(StudentStudyMajorAssign majorAssign){
//        StudyMajorStudentResponse response = new StudyMajorStudentResponse();
//        response.setId(majorAssign.getId());
//        response.setMajorCode(majorAssign.getStudyMajorCode());
//        response.setName(studyMajorRepository.findById(majorAssign.getStudyMajorCode()).get().getName());
//        response.setSemesterNumber(majorAssign.getSemesterNumber());
//        response.setStudyPlanCode(studyPlanRepository.findByMajorCode(majorAssign.getStudyMajorCode()).getStudyPlanCode());
//        return response;
//    }
//
//    public ClassGroupStudentAssignResponse ClassGroupStudentAssignResponseMapper(ClassGroupStudentAssign c){
//        ClassGroupStudentAssignResponse cr = new ClassGroupStudentAssignResponse();
//        cr.setId(c.getId());
//        cr.setGroupCode(c.getGroupCode());
//        cr.setStudentIndex(c.getStudentIndex());
//        cr.setRegisteredStudents(classGroupRepository.findById(c.getGroupCode()).get().getRegisteredStudents());
//        return cr;
//    }
//
//    public ClassGroupResponse ClassGroupResponseMapper(ClassGroup classGroup){
//        ClassGroupResponse classGroupResponse = new ClassGroupResponse(classGroup.getGroupCode(),
//                courseRepository.findById(classGroup.getCourseCode()).get().getNameInPolish(),
//                classGroup.getPlaceLimit(), classGroup.getRegisteredStudents(), ClassType.values()[classGroup.getClassTypeId().intValue() - 1],
//                null, classGroup.getCourseCode());
//        List<ClassDateTime> classTimesList = classDateTimeRepository.findAllByGroupCode(classGroup.getGroupCode());
//        classGroupResponse.setClassTimes(new ArrayList<>());
//        for (ClassDateTime cdt : classTimesList) {
//            ClassTimeResponse ctr = new ClassTimeResponse(cdt.getDate(), cdt.getStartTime(), cdt.getDurationTimeInMin());
//            classGroupResponse.getClassTimes().add(ctr);
//        }
//        return classGroupResponse;
//    }
}
