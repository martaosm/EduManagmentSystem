package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.model.Grade;
import com.example.EduManagmentSystem.request.GradeRequest;
import com.example.EduManagmentSystem.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping("/allGrades")
    public List<Grade> getAllGrades(@RequestParam String studentIndex){
        return indexService.getAllGrades(studentIndex);
    }

    @PostMapping("/addNewGrade")
    public void addNewGrade(@RequestBody GradeRequest request){
        indexService.addNewGrade(request);
    }
}
