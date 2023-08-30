package com.example.demo.repositories;

import com.example.demo.entities.PersonnelJour;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonnelJourRepository extends ModelRepository<PersonnelJour, Long> {
    @Override
    @Transactional
    @Modifying
    @Query("delete from PersonnelJour p where p.id = ?1")
    int deleteModel(Long id);

    List<PersonnelJour> findByPersonnel_Id(Long id);


}
