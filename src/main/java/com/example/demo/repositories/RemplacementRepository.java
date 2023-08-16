package com.example.demo.repositories;

import com.example.demo.entities.Remplacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemplacementRepository extends JpaRepository<Remplacement, Long> {
}
