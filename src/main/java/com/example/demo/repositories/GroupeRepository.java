package com.example.demo.repositories;

import com.example.demo.entities.Groupe;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupeRepository extends ModelRepository<Groupe, Long> {

    @Override
    @Transactional
    @Modifying
    @Query("delete from Groupe g where g.id = ?1")
    int deleteModel(Long id);
}