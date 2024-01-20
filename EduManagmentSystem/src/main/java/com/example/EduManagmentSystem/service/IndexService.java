package com.example.EduManagmentSystem.service;

import com.example.EduManagmentSystem.model.Grade;
import com.example.EduManagmentSystem.repository.GradeRepository;
import com.example.EduManagmentSystem.request.GradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IndexService {

    @Autowired
    GradeRepository gradeRepository;

    public List<Grade> getAllGrades(String studentIndex){
        return gradeRepository.findByStudentIndex(studentIndex).stream().toList();
    }

    public void addNewGrade(GradeRequest request){
        Grade grade = new Grade(request.getGradeValue(), request.getComment(), request.getGradedElement(),
                request.getGradeType().getId(), request.getStudentIndex(),
                request.getTeacherId(), request.getClassGroupCode());
        gradeRepository.save(grade);
    }
}
