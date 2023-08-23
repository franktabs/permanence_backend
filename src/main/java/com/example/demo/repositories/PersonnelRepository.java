package com.example.demo.repositories;

import com.example.demo.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Personnel findByUserId(Long userId);




}
