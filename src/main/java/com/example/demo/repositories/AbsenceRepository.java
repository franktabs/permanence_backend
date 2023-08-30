package com.example.demo.repositories;

import com.example.demo.entities.Absence;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AbsenceRepository extends ModelRepository<Absence, Long> {

    @Override
    @Transactional
    @Modifying
    @Query("delete from Absence a where a.id = ?1")
    int deleteModel(Long id);
}
