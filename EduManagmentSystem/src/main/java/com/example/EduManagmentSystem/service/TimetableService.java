package com.example.EduManagmentSystem.service;

import com.example.EduManagmentSystem.enums.ClassType;
import com.example.EduManagmentSystem.model.ClassDateTime;
import com.example.EduManagmentSystem.model.ClassGroup;
import com.example.EduManagmentSystem.model.ClassGroupStudentAssign;
import com.example.EduManagmentSystem.model.Teacher;
import com.example.EduManagmentSystem.repository.ClassDateTimeRepository;
import com.example.EduManagmentSystem.repository.ClassGroupRepository;
import com.example.EduManagmentSystem.repository.ClassGroupStudentAssignRepository;
import com.example.EduManagmentSystem.repository.TeacherRepository;
import com.example.EduManagmentSystem.response.ClassGroupResponse;
import com.example.EduManagmentSystem.response.ClassTimeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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

    public List<ClassGroupResponse> getClassGroupsForTheCourse(String courseCode){
        List<ClassGroupResponse> response = new ArrayList<>();
        List<ClassGroup> classGroupsList = classGroupRepository.findAllByCourseCode(courseCode);
        for(ClassGroup cg : classGroupsList){
            getClassGroupResponse(cg, response);
        }
        return response;
    }

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public void setTeacherIdForClassGroup(String groupCode, Long teacherId) throws Exception {
        if(classGroupRepository.findByGroupCode(groupCode).isPresent()){
            ClassGroup classGroup = classGroupRepository.findByGroupCode(groupCode).get();
            classGroup.setTeacherId(teacherId);
            classGroupRepository.save(classGroup);
        }else{
            throw new Exception("Class group with this id doesn't exist");
        }
    }

    public List<ClassGroupResponse> getAllClassGroupsForStudent(String index) throws Exception {
        List<ClassGroupResponse> response = new ArrayList<>();
        List<ClassGroupStudentAssign> classGroupStudentList = cgsRepository.findAllByStudentIndex(index);
        for(ClassGroupStudentAssign cgs : classGroupStudentList){
            if(classGroupRepository.findByGroupCode(cgs.getGroupCode()).isPresent()){
                ClassGroup cg = classGroupRepository.findByGroupCode(cgs.getGroupCode()).get();
                getClassGroupResponse(cg, response);
            }else{
                throw new Exception("Class group with this code doesn't exist");
            }
        }
        return response;
    }

    public void getCoursesAssignedToStudyPlan()

    public void getClassGroupResponse(ClassGroup cg, List<ClassGroupResponse> response){
        ClassGroupResponse classGroupResponse = new ClassGroupResponse(cg.getGroupCode(), cg.getPlaceLimit(), cg.getRegisteredStudents(),
                ClassType.values()[cg.getClassTypeId().intValue()-1], cg.getTeacherId(), cg.getCourseCode());
        List<ClassDateTime> classTimesList = classDateTimeRepository.findAllByGroupCode(cg.getGroupCode());
        classGroupResponse.setClassTimes(new ArrayList<>());
        for(ClassDateTime cdt : classTimesList){
            ClassTimeResponse ctr = new ClassTimeResponse(cdt.getDate(), cdt.getStartTime(), cdt.getDurationTimeInMin());
            classGroupResponse.getClassTimes().add(ctr);
        }
        response.add(classGroupResponse);
    }
}
