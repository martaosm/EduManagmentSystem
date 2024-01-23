package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, String> {
}
