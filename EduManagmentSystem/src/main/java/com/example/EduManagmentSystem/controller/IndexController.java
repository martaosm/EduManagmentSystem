package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.request.GradeRequest;
import com.example.EduManagmentSystem.response.ClassGroupTeacherResponse;
import com.example.EduManagmentSystem.response.GradeResponse;
import com.example.EduManagmentSystem.response.StudentGradeResponse;
import com.example.EduManagmentSystem.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping("/allStudentGrades")
    public List<GradeResponse> getAllGradesForStudent(@RequestParam String studentIndex){
        return indexService.getAllGrades(studentIndex);
    }

    @GetMapping("/getAllClassesForTeacher")
    public List<ClassGroupTeacherResponse> getAllClassesForTeacher(@RequestParam Long teacherId){
        return indexService.getAllClassesForTeacher(teacherId);
    }

    @GetMapping("/getAllStudentsForClassGroup")
    public List<StudentGradeResponse> getAllStudentsForClassGroup(@RequestParam String groupCode) throws Exception {
        return indexService.getAllStudentsForClassGroup(groupCode);
    }

    @PostMapping("/addNewGrade")
    public void addNewGrade(@RequestBody GradeRequest request){
        indexService.addNewGrade(request);
    }
}
