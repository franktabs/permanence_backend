package com.example.demo.repositories;

import com.example.demo.entities.Permanence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermanenceRepository extends JpaRepository<Permanence, Long> {
    List<Permanence> findByPersonnelJours_Personnel_IdOrPersonnelNuits_Personnel_Id(Long id, Long id1);
}
