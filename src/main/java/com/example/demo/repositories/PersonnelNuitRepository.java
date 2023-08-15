package com.example.demo.repositories;

import com.example.demo.entities.PersonnelNuit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonnelNuitRepository extends JpaRepository<PersonnelNuit, Long> {

    @Modifying
    @Query("delete from PersonnelNuit p where p.id = ?1")
    int deletePersonnelNuit(Long id);




}
