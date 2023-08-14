package com.example.demo.repositories;

import com.example.demo.entities.PersonnelNuit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelNuitRepository extends JpaRepository<PersonnelNuit, Long> {
}
