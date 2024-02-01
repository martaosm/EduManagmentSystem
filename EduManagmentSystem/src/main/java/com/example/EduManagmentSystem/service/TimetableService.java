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
public class TimetableService {

    @Autowired
    ClassGroupRepository classGroupRepository;

    @Autowired
    ClassDateTimeRepository classDateTimeRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ClassGroupStudentAssignRepository cgsRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PersonalDataRepository personalDataRepository;





    public List<TeacherResponse> getAllTeachers() {
        List<TeacherResponse> response = new ArrayList<>();
        for(Teacher t : teacherRepository.findAll()){
            response.add(TeacherResponseMapper(t));
        }
        return response;
    }

    public ClassGroupResponse setTeacherIdForClassGroup(String groupCode, Long teacherId) throws Exception {
        if (classGroupRepository.findByGroupCode(groupCode).isPresent()) {
            ClassGroup classGroup = classGroupRepository.findByGroupCode(groupCode).get();
            classGroup.setTeacherId(teacherId);
            classGroupRepository.save(classGroup);
        } else {
            throw new Exception("Class group with this id doesn't exist");
        }
        return ClassGroupResponseMapper(classGroupRepository.findByGroupCode(groupCode).get());
    }

    public List<ClassGroupResponse> getAllClassGroupsForStudent(String index) throws Exception {
        List<ClassGroupResponse> response = new ArrayList<>();
        List<ClassGroupStudentAssign> classGroupStudentList = cgsRepository.findAllByStudentIndex(index);
        for (ClassGroupStudentAssign cgs : classGroupStudentList) {
            if (classGroupRepository.findByGroupCode(cgs.getGroupCode()).isPresent()) {
                ClassGroup cg = classGroupRepository.findByGroupCode(cgs.getGroupCode()).get();
                response.add(ClassGroupResponseMapper(cg));
            } else {
                throw new Exception("Class group with this code doesn't exist");
            }
        }
        return response;
    }
    
    public List<ClassGroupResponse> getAllClassGroupsForStudyPlan(String studyPlanCode, int semesterNumber) throws UnknownHostException {
        List<ClassGroupResponse> response = new ArrayList<>();
        List<CourseResponse> courseResponses = getCoursesAssignedToStudyPlan(studyPlanCode, semesterNumber)
                .stream().filter(s -> s.getSemesterNumber() == semesterNumber).toList().get(0).getCourses();
        for(CourseResponse courseResponse : courseResponses){
            List<ClassGroup> classGroups = classGroupRepository.findAllByCourseCode(courseResponse.getCourseCode());
            for(ClassGroup cp : classGroups){
                response.add(ClassGroupResponseMapper(classGroupRepository.findByGroupCode(cp.getGroupCode()).get()));
            }
        }
        return response;
    }


    public List<SemesterResponse> getCoursesAssignedToStudyPlan(String studyPlanCode, int semesterNumber) throws UnknownHostException {
        // url/port do zmiany
        //final String HOSTNAME = InetAddress.getLocalHost().getHostName();

        HashMap<String, Object> params2 = new HashMap<>();
        params2.put("studyPlanCode", studyPlanCode);

        ResponseEntity<List<SemesterResponse>> courses
                = new RestTemplate().exchange(
                "http://".concat(System.getenv("STUDY_PLAN_SERVICE_HOST"))
                        .concat(":").concat(System.getenv("STUDY_PLAN_SERVICE_PORT"))
                        .concat("/getAllCoursesStudyPlan?studyPlanCode={studyPlanCode}"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){},
                params2);

        return courses.getBody();
        //"http://".concat(HOSTNAME).concat(":8081/getAllCoursesStudyPlan?studyPlanCode={studyPlanCode}"),
    }

    public List<StudyPlanResponse> getAllStudyPlans() throws UnknownHostException {
        // url/port do zmiany
        final String HOSTNAME = InetAddress.getLocalHost().getHostName();
        ResponseEntity<List<StudyPlanResponse>> studyPlans
                = new RestTemplate().exchange(
                "http://".concat(System.getenv("STUDY_PLAN_SERVICE_HOST"))
                        .concat(":").concat(System.getenv("STUDY_PLAN_SERVICE_PORT"))
                        .concat("/getAllStudyPlans"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        return studyPlans.getBody();
    }

    public ClassGroupResponse ClassGroupResponseMapper(ClassGroup classGroup){
        TeacherResponse teacherResponse = new TeacherResponse();
        if(classGroup.getTeacherId()!=null && teacherRepository.findById(classGroup.getTeacherId()).isPresent()){
            teacherResponse = TeacherResponseMapper(teacherRepository.findById(classGroup.getTeacherId()).get());
        }
        ClassGroupResponse classGroupResponse = new ClassGroupResponse(classGroup.getGroupCode(),
                courseRepository.findById(classGroup.getCourseCode()).get().getNameInPolish(),
                classGroup.getPlaceLimit(), classGroup.getRegisteredStudents(), ClassType.values()[classGroup.getClassTypeId().intValue() - 1],
                teacherResponse, classGroup.getCourseCode());
        ClassDateTime classTime = classDateTimeRepository.findAllByGroupCode(classGroup.getGroupCode()).get(0);
        classGroupResponse.setClassDate(classTime.getDate().toString().concat(", ").concat(classTime.getStartTime()));
        return classGroupResponse;
    }

    public TeacherResponse TeacherResponseMapper(Teacher teacher){
        Account account = accountRepository.getAccountById(teacher.getAccountId());
        PersonalData personalData = personalDataRepository.getPersonalDataById(account.getPersonalDataId());
        return new TeacherResponse(teacher.getId(), personalData.getName(), personalData.getSurname(), teacher.getTitle());
    }
}
