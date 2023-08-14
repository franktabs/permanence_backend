package com.example.demo.repositories;

import com.example.demo.entities.PersonnelJour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelJourRepository extends JpaRepository<PersonnelJour, Long> {
}
