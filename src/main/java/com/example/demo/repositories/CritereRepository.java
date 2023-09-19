package com.example.demo.repositories;

import com.example.demo.entities.Critere;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CritereRepository extends ModelRepository<Critere, Long> {
    @Override
    @Transactional
    @Modifying
    @Query("delete from Critere c where c.id = ?1")
    int deleteModel(Long id);
}
