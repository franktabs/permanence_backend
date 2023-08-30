package com.example.demo.repositories;

import com.example.demo.entities.Personnel;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PersonnelRepository extends ModelRepository<Personnel, Long> {
    Personnel findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("delete from Personnel p where p.id = ?1")
    int deleteModel(Long id);




}
