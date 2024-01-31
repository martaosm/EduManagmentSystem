package com.example.EduManagmentSystem.service;

import com.example.EduManagmentSystem.enums.ClassType;
import com.example.EduManagmentSystem.enums.GradeType;
import com.example.EduManagmentSystem.enums.GradeValue;
import com.example.EduManagmentSystem.model.*;
import com.example.EduManagmentSystem.repository.*;
import com.example.EduManagmentSystem.request.GradeRequest;
import com.example.EduManagmentSystem.response.ClassGroupResponse;
import com.example.EduManagmentSystem.response.ClassGroupTeacherResponse;
import com.example.EduManagmentSystem.response.GradeResponse;
import com.example.EduManagmentSystem.response.StudentGradeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<GradeResponse> getAllGrades(String studentIndex){
        List<GradeResponse> responses = new ArrayList<>();
        List<Grade> grades = gradeRepository.findAllByStudentIndexAndGradeTypeId(studentIndex, GradeType.SEMESTER.getId());
        for(Grade grade : grades){
            responses.add(GradeResponseMapper(grade));
        }
        return responses;
    }

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

    public void addNewGrade(GradeRequest request){
        Grade grade = new Grade();
        grade.setGradeTypeId(GradeType.SEMESTER.getId());
        grade.setStudentIndex(request.getStudentIndex());
        grade.setGradeValue(request.getGradeValue());
        grade.setClassGroupCode(request.getGroupCode());
        grade.setTeacherId(request.getTeacherId());
        gradeRepository.save(grade);
    }

    public GradeResponse GradeResponseMapper(Grade grade){
        GradeResponse response = new GradeResponse();
        response.setGradeValue(GradeValue.valueOf(grade.getGradeValue().toString()));
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
                    response.setGradeId(grade.getId());
                    response.setGradeValue(GradeValue.valueOf(grade.getGradeValue().toString()));
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
