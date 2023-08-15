package com.example.demo.repositories;

import com.example.demo.entities.PersonnelJour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonnelJourRepository extends JpaRepository<PersonnelJour, Long> {
    @Transactional
    @Modifying
    @Query("delete from PersonnelJour p where p.id = ?1")
    int deletePersonnelJour(Long id);


}
