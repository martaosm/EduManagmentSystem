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

@Service
public class EnrollmentService {

    @Autowired
    StudentStudyMajorAssignRepository studentStudyMajorAssignRepository;

    @Autowired
    StudyPlanRepository studyPlanRepository;

    @Autowired
    StudyMajorRepository studyMajorRepository;

    @Autowired
    ClassGroupStudentAssignRepository classGroupStudentAssignRepository;

    @Autowired
    ClassGroupRepository classGroupRepository;

    @Autowired
    ClassDateTimeRepository classDateTimeRepository;

    @Autowired
    CourseRepository courseRepository;


    public List<StudyMajorStudentResponse> getAllMajorsForStudent(String studentIndex){
        List<StudyMajorStudentResponse> response = new ArrayList<>();
        List<StudentStudyMajorAssign> majors = studentStudyMajorAssignRepository.findAllByStudentIndex(studentIndex);
        for(StudentStudyMajorAssign s : majors){
            response.add(StudentStudyMajorResponseMapper(s));
        }
        return response;
    }

    public List<EnrollCourseResponse> getCoursesForStudent(String studentIndex, String studyPlanCode, int semesterNumber) throws UnknownHostException {
        //url/port do zmiany

        //final String HOSTNAME = InetAddress.getLocalHost().getHostName();
        HashMap<String, Object> params = new HashMap<>();
        params.put("studyPlanCode", studyPlanCode);

        ResponseEntity<List<SemesterResponse>> courses
                = new RestTemplate().exchange(
                "http://".concat(System.getenv("STUDY_PLAN_SERVICE_HOST"))
                        .concat(":").concat(System.getenv("STUDY_PLAN_SERVICE_PORT"))
                        .concat("/getAllCoursesStudyPlan?studyPlanCode={studyPlanCode}"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){},
                params);


        List<CourseResponse> studentCourses = courses.getBody()
                .stream().filter(s -> s.getSemesterNumber() == semesterNumber).toList().get(0).getCourses();

        List<ClassGroupStudentAssign> classGroupStudentAssigns = classGroupStudentAssignRepository.findAllByStudentIndex(studentIndex);
        //List<ClassGroup> classGroupsStudent = new ArrayList<>();
        List<String> courseCodesStudent = new ArrayList<>();
        for(ClassGroupStudentAssign cga : classGroupStudentAssigns){
            courseCodesStudent.add(classGroupRepository.findById(cga.getGroupCode()).get().getCourseCode());
            //classGroupsStudent.add(classGroupRepository.findById(cga.getGroupCode()).get());
        }
        List<EnrollCourseResponse> responses = new ArrayList<>();
        for(CourseResponse c : studentCourses){
            if(courseCodesStudent.contains(c.getCourseCode())){
                responses.add(EnrollCourseResponseMapper(c, true));
            }else{
                responses.add(EnrollCourseResponseMapper(c, false));
            }
        }
        return responses;
    }

    public List<ClassGroupResponse> getClassesForCourses(String studyPlanCode, int semesterNumber, String courseCode) throws Exception {
        //url/port do zmiany

        //final String HOSTNAME = InetAddress.getLocalHost().getHostName();
        HashMap<String, Object> params = new HashMap<>();
        params.put("studyPlanCode", studyPlanCode);
        params.put("semesterNumber", semesterNumber);
        
        ResponseEntity<List<ClassGroupResponse>> classes
                = new RestTemplate().exchange(
                "http://".concat(System.getenv("TIMETABLE_SERVICE_HOST"))
                        .concat(":").concat(System.getenv("TIMETABLE_SERVICE_PORT"))
                        .concat("/getTimetableForStudyPlan?studyPlanCode={studyPlanCode}&semesterNumber={semesterNumber}"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){},
                params);

        List<ClassGroupResponse> response = new ArrayList<>();
        if(classes.getBody() != null){
            for(ClassGroupResponse cgr : classes.getBody()){
                if(cgr.getCourseCode().equals(courseCode)){
                    response.add(cgr);
                }
            }
        }else{
            throw new Exception("No classes found for this study plan");
        }
        return response;
    }

    public ClassGroupStudentAssignResponse signForClass(String studentIndex, String groupCode) throws Exception {
        if(classGroupStudentAssignRepository.findByStudentIndexAndGroupCode(studentIndex, groupCode) == null){
            if(classGroupRepository.findById(groupCode).isPresent()){
                ClassGroup classGroup = classGroupRepository.findById(groupCode).get();
                if(classGroup.getRegisteredStudents()<classGroup.getPlaceLimit()){
                    ClassGroupStudentAssign classGroupStudentAssign = new ClassGroupStudentAssign();
                    classGroupStudentAssign.setStudentIndex(studentIndex);
                    classGroupStudentAssign.setGroupCode(groupCode);
                    classGroupStudentAssignRepository.save(classGroupStudentAssign);
                    classGroup.setRegisteredStudents(classGroup.getRegisteredStudents()+1);
                    classGroupRepository.save(classGroup);
                    return ClassGroupStudentAssignResponseMapper(classGroupStudentAssignRepository.findByStudentIndexAndGroupCode(studentIndex, groupCode));
                }else{
                    throw new Exception("Place limit was reached. Student can't sign up for this class");
                }
            }else{
                throw new Exception("Class with this code doesn't exist");
            }
        }else{
            throw new Exception("Student is already signed up for this class");
        }
    }

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

    public ClassGroupResponse increasePlaceLimit(String groupCode, int newPlaceLimit) throws Exception {
        if(classGroupRepository.findById(groupCode).isPresent()){
            ClassGroup classGroup = classGroupRepository.findById(groupCode).get();
            classGroup.setPlaceLimit(newPlaceLimit);
            classGroupRepository.save(classGroup);
            return ClassGroupResponseMapper(classGroup);
        }else{
            throw new Exception("Class group with this code doesn't exist");
        }

    }

    public StudyMajorStudentResponse StudentStudyMajorResponseMapper(StudentStudyMajorAssign majorAssign){
        StudyMajorStudentResponse response = new StudyMajorStudentResponse();
        response.setId(majorAssign.getId());
        response.setMajorCode(majorAssign.getStudyMajorCode());
        response.setName(studyMajorRepository.findById(majorAssign.getStudyMajorCode()).get().getName());
        response.setSemesterNumber(majorAssign.getSemesterNumber());
        response.setStudyPlanCode(studyPlanRepository.findByMajorCode(majorAssign.getStudyMajorCode()).getStudyPlanCode());
        return response;
    }

    public ClassGroupStudentAssignResponse ClassGroupStudentAssignResponseMapper(ClassGroupStudentAssign c){
        ClassGroupStudentAssignResponse cr = new ClassGroupStudentAssignResponse();
        cr.setId(c.getId());
        cr.setGroupCode(c.getGroupCode());
        cr.setStudentIndex(c.getStudentIndex());
        cr.setRegisteredStudents(classGroupRepository.findById(c.getGroupCode()).get().getRegisteredStudents());
        return cr;
    }

    public ClassGroupResponse ClassGroupResponseMapper(ClassGroup classGroup){
        TeacherResponse teacherResponse = new TeacherResponse();
//        if(classGroup.getTeacherId()!=null && teacherRepository.findById(classGroup.getTeacherId()).isPresent()){
//            teacherResponse = TeacherResponseMapper(teacherRepository.findById(classGroup.getTeacherId()).get());
//        }
        ClassGroupResponse classGroupResponse = new ClassGroupResponse(classGroup.getGroupCode(),
                courseRepository.findById(classGroup.getCourseCode()).get().getNameInPolish(),
                classGroup.getPlaceLimit(), classGroup.getRegisteredStudents(), ClassType.values()[classGroup.getClassTypeId().intValue() - 1],
                teacherResponse, classGroup.getCourseCode());
        ClassDateTime classTime = classDateTimeRepository.findAllByGroupCode(classGroup.getGroupCode()).get(0);
        classGroupResponse.setClassDate(classTime.getDate().toString().concat(", ").concat(classTime.getStartTime()));
        return classGroupResponse;
    }

    public EnrollCourseResponse EnrollCourseResponseMapper(CourseResponse course, Boolean isEnrolled){
        EnrollCourseResponse response = new EnrollCourseResponse();
        response.setCourseCode(course.getCourseCode());
        response.setCourseNameInPolish(course.getNameInPolish());
        response.setClassType(course.getClassType());
        response.setIsStudentEnrolled(isEnrolled);
        return response;
    }

}
