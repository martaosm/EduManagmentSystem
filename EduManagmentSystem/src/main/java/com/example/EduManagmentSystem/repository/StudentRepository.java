package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
