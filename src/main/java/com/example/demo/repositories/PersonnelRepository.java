package com.example.demo.repositories;

import com.example.demo.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Personnel findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("delete from Personnel p where p.id = ?1")
    int deletePersonnel(Long id);




}
