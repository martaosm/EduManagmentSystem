package com.example.EduManagmentSystem.repository;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.model.MandatoryBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MandatoryBlockRepository extends JpaRepository<MandatoryBlock, Long> {
    Optional<MandatoryBlock> findBySemesterId(Long semesterId);
}
