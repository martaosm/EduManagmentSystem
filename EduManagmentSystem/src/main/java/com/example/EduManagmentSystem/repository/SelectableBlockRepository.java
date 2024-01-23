package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.SelectableBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectableBlockRepository extends JpaRepository<SelectableBlock, Long> {
}
