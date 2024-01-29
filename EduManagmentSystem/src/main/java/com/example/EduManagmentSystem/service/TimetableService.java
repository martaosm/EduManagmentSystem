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
    ClassPlanRepository classPlanRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PersonalDataRepository personalDataRepository;

    private final String HOSTNAME = InetAddress.getLocalHost().getHostName();

    public TimetableService() throws UnknownHostException {
    }

    public List<ClassGroupResponse> getClassGroupsForTheCourse(String courseCode) {
        List<ClassGroupResponse> response = new ArrayList<>();
        List<ClassGroup> classGroupsList = classGroupRepository.findAllByCourseCode(courseCode);
        for (ClassGroup cg : classGroupsList) {
            response.add(ClassGroupResponseMapper(cg));
        }
        return response;
    }

    public List<TeacherResponse> getAllTeachers() {
        List<TeacherResponse> response = new ArrayList<>();
        for(Teacher t : teacherRepository.findAll()){
            response.add(TeacherResponseMapper(t));
        }
        return response;
    }

    public void setTeacherIdForClassGroup(String groupCode, Long teacherId) throws Exception {
        if (classGroupRepository.findByGroupCode(groupCode).isPresent()) {
            ClassGroup classGroup = classGroupRepository.findByGroupCode(groupCode).get();
            classGroup.setTeacherId(teacherId);
            classGroupRepository.save(classGroup);
        } else {
            throw new Exception("Class group with this id doesn't exist");
        }
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


    public List<ClassGroupResponse> getAllClassGroupsForMajor(String majorCode){
        List<StudyPlanResponse> studyPlanResponses = getAllStudyPlans();
        for(StudyPlanResponse studyPlanResponse : studyPlanResponses){
            if(studyPlanResponse.getMajorName())
        }
    }

    public List<CourseResponse> getCoursesAssignedToStudyPlan(String majorCode) throws UnknownHostException {
        HashMap<String, String> params1 = new HashMap<>();
        params1.put("majorCode", majorCode);

        ResponseEntity<StudyPlanResponse> studyPlan
                = new RestTemplate().getForEntity(
                "http://".concat(HOSTNAME).concat(":8081/getStudyPlanByMajorCode?majorCode={majorCode}"),
                StudyPlanResponse.class, params1);

        HashMap<String, String> params2 = new HashMap<>();
        params2.put("studyPlanCode", studyPlan.getBody().getStudyPlanCode());

        ResponseEntity<List<CourseResponse>> courses
                = new RestTemplate().exchange(
                        "http://".concat(HOSTNAME).concat(":8081//getAllCoursesStudyPlan?studyPlanCode={studyPlanCode}"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){},
                params2);

        return courses.getBody();
    }

    public List<StudyPlanResponse> getAllStudyPlans(){
        ResponseEntity<List<StudyPlanResponse>> studyPlans
                = new RestTemplate().exchange(
                "http://".concat(HOSTNAME).concat(":8081/getAllStudyPlans"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        return studyPlans.getBody();
    }

    public ClassGroupResponse ClassGroupResponseMapper(ClassGroup classGroup){
        ClassGroupResponse classGroupResponse = new ClassGroupResponse(classGroup.getGroupCode(), classGroup.getPlaceLimit(), classGroup.getRegisteredStudents(),
                ClassType.values()[classGroup.getClassTypeId().intValue() - 1], TeacherResponseMapper(teacherRepository.findById(classGroup.getTeacherId()).get()), classGroup.getCourseCode());
        List<ClassDateTime> classTimesList = classDateTimeRepository.findAllByGroupCode(classGroup.getGroupCode());
        classGroupResponse.setClassTimes(new ArrayList<>());
        for (ClassDateTime cdt : classTimesList) {
            ClassTimeResponse ctr = new ClassTimeResponse(cdt.getDate(), cdt.getStartTime(), cdt.getDurationTimeInMin());
            classGroupResponse.getClassTimes().add(ctr);
        }
        return classGroupResponse;
    }

    public TeacherResponse TeacherResponseMapper(Teacher teacher){
        Account account = accountRepository.getAccountById(teacher.getAccountId());
        PersonalData personalData = personalDataRepository.getPersonalDataByAccountId(account.getId());
        return new TeacherResponse(teacher.getId(), personalData.getName(), personalData.getSurname(), teacher.getTitle());
    }
}
