package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.ClassDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDateTimeRepository extends JpaRepository<ClassDateTime, Long> {
    List<ClassDateTime> findAllByGroupCode(String groupCode);
}
