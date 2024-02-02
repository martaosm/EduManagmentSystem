package com.example.EduManagmentSystem.service;

import com.example.EduManagmentSystem.enums.ClassType;
import com.example.EduManagmentSystem.enums.GradeType;
import com.example.EduManagmentSystem.enums.GradeValue;
import com.example.EduManagmentSystem.model.*;
import com.example.EduManagmentSystem.repository.*;
import com.example.EduManagmentSystem.request.GradeRequest;
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
public class IndexService {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    ClassGroupRepository classGroupRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ClassDateTimeRepository classDateTimeRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassGroupStudentAssignRepository classGroupStudentAssignRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PersonalDataRepository personalDataRepository;

    @Autowired
    StudentStudyMajorAssignRepository studentStudyMajorAssignRepository;

    @Autowired
    StudyPlanRepository studyPlanRepository;


    public List<AllGradesResponse> getAllGrades(String studentIndex) throws UnknownHostException {

        HashMap<Integer, List<GradeResponse>> response = new HashMap<>();

        List<StudentStudyMajorAssign> majors = studentStudyMajorAssignRepository.findAllByStudentIndex(studentIndex);

        List<AllGradesResponse> allGradesResponses = new ArrayList<>();
//
        final String HOSTNAME = InetAddress.getLocalHost().getHostName();
        HashMap<String, Object> params1 = new HashMap<>();
        params1.put("studentIndex", studentIndex);
//comment



        ResponseEntity<List<SemesterResponse>> courses
                = new RestTemplate().exchange(
                "http://".concat(System.getenv("ENROLLMENT_SERVICE_HOST"))
                                        .concat(":").concat(System.getenv("ENROLLMENT_SERVICE_PORT"))
                                        .concat("/getCoursesForStudent?studentIndex={studentIndex}"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){},
                params1);



        for(SemesterResponse s : courses.getBody()){
            response.put(s.getSemesterNumber(), new ArrayList<>());
        }

        List<ClassGroupStudentAssign> classGroupStudentAssigns = classGroupStudentAssignRepository.findAllByStudentIndex(studentIndex);
        for(ClassGroupStudentAssign x : classGroupStudentAssigns){
            for(SemesterResponse r : courses.getBody()){
                ClassGroup classGroup = classGroupRepository.findById(x.getGroupCode()).get();
                if(r.getCourses()
                        .stream().filter(y -> y.getCourseCode()
                                .equals(classGroup.getCourseCode()))
                        .toList().size()>0){
                    response.get(r.getSemesterNumber()).add(GradeResponseMapper(
                            gradeRepository.findByStudentIndexAndClassGroupCodeAndGradeTypeId(studentIndex, classGroup.getGroupCode(), GradeType.SEMESTER.getId())));
                }
            }
        }

        for(SemesterResponse s : courses.getBody()){
            AllGradesResponse allGradesResponse = new AllGradesResponse();
            allGradesResponse.setSemesterNumber(s.getSemesterNumber());
            allGradesResponse.setGrades(response.get(s.getSemesterNumber()));
            allGradesResponses.add(allGradesResponse);
        }

        return allGradesResponses;

    }
    // "http://".concat(System.getenv("STUDY_PLAN_SERVICE_HOST"))
    //                                .concat(":").concat("STUDY_PLAN_SERVICE_PORT")
    //                                .concat("/getClassesForCourse?studyPlanCode={studyPlanCode}&semesterNumber={semesterNumber}&courseCode={courseCode}"),
    //

    public List<ClassGroupTeacherResponse> getAllClassesForTeacher(Long teacherId){
        List<ClassGroup> classGroups = classGroupRepository.findAllByTeacherId(teacherId);
        List<ClassGroupTeacherResponse> responses = new ArrayList<>();
        for(ClassGroup classGroup : classGroups){
            responses.add(ClassGroupResponseMapper(classGroup));
        }
        return responses;
    }

    public List<StudentGradeResponse>  getAllStudentsForClassGroup(String groupCode) throws Exception {
        List<ClassGroupStudentAssign> classGroupStudentAssigns = classGroupStudentAssignRepository.findAllByGroupCode(groupCode);
        List<StudentGradeResponse> responses = new ArrayList<>();
        for(ClassGroupStudentAssign cgsa : classGroupStudentAssigns){
            responses.add(StudentGradeResponseMapper(cgsa));
        }
        return responses;
    }

    public GradeResponse addNewGrade(GradeRequest request) throws Exception {
        Grade grade = new Grade();
        grade.setGradeTypeId(GradeType.SEMESTER.getId());
        grade.setStudentIndex(request.getStudentIndex());
        grade.setGradeValue(request.getGradeValue());
        grade.setClassGroupCode(request.getGroupCode());
        grade.setTeacherId(request.getTeacherId());
        gradeRepository.save(grade);
        grade = gradeRepository.findByStudentIndexAndClassGroupCodeAndGradeTypeId(request.getStudentIndex(), request.getGroupCode(), GradeType.SEMESTER.getId());
        if(grade != null){
            return GradeResponseMapper(grade);
        }else{
            throw new Exception("Grade not saved");
        }
    }

    public GradeResponse GradeResponseMapper(Grade grade){
        GradeResponse response = new GradeResponse();
        response.setGradeValue(grade.getGradeValue());
        ClassGroup classGroup = classGroupRepository.findById(grade.getClassGroupCode()).get();
        Course course = courseRepository.findById(classGroup.getCourseCode()).get();
        response.setCourseName(course.getNameInPolish());
        response.setClassType(ClassType.values()[classGroup.getClassTypeId().intValue()-1]);
        return response;
    }

    public ClassGroupTeacherResponse ClassGroupResponseMapper(ClassGroup classGroup){
        ClassGroupTeacherResponse response = new ClassGroupTeacherResponse();
        ClassDateTime classTime = classDateTimeRepository.findAllByGroupCode(classGroup.getGroupCode()).get(0);
        response.setClassType(ClassType.values()[classGroup.getClassTypeId().intValue()-1]);
        response.setClassDate(classTime.getDate().toString().concat(", ").concat(classTime.getStartTime()));
        response.setGroupCode(classGroup.getGroupCode());
        Course course = courseRepository.findById(classGroup.getCourseCode()).get();
        response.setCourseName(course.getNameInPolish());
        response.setCourseCode(course.getCourseCode());
        response.setRegisteredStudent(classGroup.getRegisteredStudents());
        return response;
    }

    public StudentGradeResponse StudentGradeResponseMapper(ClassGroupStudentAssign classGroupStudentAssign) throws Exception {
        if(studentRepository.findById(classGroupStudentAssign.getStudentIndex()).isPresent()){
            Student student = studentRepository.findById(classGroupStudentAssign.getStudentIndex()).get();
            if(accountRepository.findById(student.getAccountId()).isPresent()){
                Account account = accountRepository.findById(student.getAccountId()).get();
                if(personalDataRepository.findById(account.getPersonalDataId()).isPresent()){
                    PersonalData personalData = personalDataRepository.findById(account.getPersonalDataId()).get();
                    Grade grade = gradeRepository.findByStudentIndexAndClassGroupCodeAndGradeTypeId(
                            student.getIndexNumber(), classGroupStudentAssign.getGroupCode(), GradeType.SEMESTER.getId());

                    StudentGradeResponse response = new StudentGradeResponse();
                    response.setIndex(student.getIndexNumber());
                    response.setFirstName(personalData.getName());
                    response.setLastName(personalData.getSurname());
                    if(grade != null){
                        //response.setGradeId(grade.getId());
                        response.setGradeValue(grade.getGradeValue());
                    }
                    return response;
                }else{
                    throw new Exception("No personal data with this id in DB");
                }
            }else{
                throw new Exception("No account with this id in DB");
            }
        }else{
            throw new Exception("No student with this index in DB");
        }
    }
}
