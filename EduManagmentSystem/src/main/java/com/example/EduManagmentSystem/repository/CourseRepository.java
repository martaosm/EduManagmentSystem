package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.Course;
import com.example.EduManagmentSystem.model.CourseGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
