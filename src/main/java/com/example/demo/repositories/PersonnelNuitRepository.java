package com.example.demo.repositories;

import com.example.demo.entities.PersonnelNuit;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonnelNuitRepository extends ModelRepository<PersonnelNuit, Long> {

    @Override
    @Modifying
    @Query("delete from PersonnelNuit p where p.id = ?1")
    int deleteModel(Long id);

    List<PersonnelNuit> findByPersonnel_Id(Long id);




}
