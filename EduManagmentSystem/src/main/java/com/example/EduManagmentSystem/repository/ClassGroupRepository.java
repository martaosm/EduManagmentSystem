package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, String> {

    Optional<ClassGroup> findByGroupCode(String groupCode);
    List<ClassGroup> findAllByCourseCode(String courseCode);
    List<ClassGroup> findAllByTeacherId(Long teacherId);
}
